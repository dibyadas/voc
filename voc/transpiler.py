import ast
import os
import sys

from .python.ast import Visitor
from .python.debug import dump


class testing(ast.NodeTransformer):
    def visit_BinOp(self,node):
        if isinstance(node.left,ast.Name):
            attr = {
                        ast.Add: '__add__',
                        ast.Sub: '__sub__',
                        ast.Mult: '__mul__',
                        ast.Div: '__truediv__',
                        ast.FloorDiv: '__floordiv__',
                        ast.Mod: '__mod__',
                        ast.Pow: '__pow__',
                        ast.LShift: '__lshift__',
                        ast.RShift: '__rshift__',
                        ast.BitOr: '__or__',
                        ast.BitXor: '__xor__',
                        ast.BitAnd: '__and__',
                        # ast.MatMult:
                    }[type(node.op)]
            attr2 = ast.Attribute(value=node.left,attr=attr,ctx=ast.Load())
#             node.func = attr2
#             node.args = [node.right]
#             node.keywords = []
            node2 = ast.Call(func=attr2,args=[node.right],keywords=[])
            node2 = ast.copy_location(node2,node)
            node2 = ast.fix_missing_locations(node2)
            return node2
        else:
            return node


def transpile(input, prefix='.', outdir=None, namespace='python', verbosity=0):
    transpiler = Transpiler(namespace=namespace, verbosity=verbosity)

    for file_or_dir in input:
        if os.path.isfile(file_or_dir):
            if verbosity:
                print("Compiling %s ..." % file_or_dir)

            with open(file_or_dir) as source:
                ast_module = ast.parse(source.read(), mode='exec')
                ast_module = testing().visit(ast_module)
                transpiler.transpile(file_or_dir, ast_module, prefix)
        elif os.path.isdir(file_or_dir):
            for root, dirs, files in os.walk(file_or_dir, followlinks=True):
                for filename in files:
                    if os.path.splitext(filename)[1] == '.py':
                        source_file = os.path.join(root, filename)
                        if verbosity:
                            print("Compiling %s ..." % source_file)
                        with open(source_file) as source:
                            ast_module = ast.parse(source.read(), mode='exec')
                            ast_module = testing().visit(ast_module)
                            transpiler.transpile(source_file, ast_module, prefix)
        else:
            print("Unknown source file: %s" % file_or_dir, file=sys.stderr)

    transpiler.write(outdir)


class Transpiler:
    def __init__(self, namespace="python", verbosity=0):
        self.namespace = namespace
        self.classfiles = []
        self.verbosity = verbosity

    def write(self, outdir):
        # Create directory tree to store classfile
        if outdir:
            basedir = [outdir]
        else:
            basedir = []

        for namespace, class_name, javaclassfile in self.classfiles:
            dirname = os.path.join(*(basedir + namespace.split('.')))
            classfilename = os.path.join(dirname, '%s.class' % class_name)

            try:
                os.makedirs(os.path.dirname(classfilename))
            except FileExistsError:
                pass

            if self.verbosity:
                print("Writing %s ..." % classfilename)

            with open(classfilename, 'wb') as out:
                javaclassfile.write(out)

    def transpile(self, filename, ast_module, prefix):
        "Transpile a Python source file into class files"
        # Determine what portion of the filename is part of the
        # common source directory, and which is namespace.
        common = os.path.commonprefix([
            os.path.abspath(prefix),
            os.path.abspath(filename)
        ])

        self.transpile_code(os.path.abspath(filename)[len(common) + 1:], ast_module)

    def transpile_string(self, filename, code_string):
        "Transpile a string containing Python code into class files"
        ast_module = ast.parse(code_string, mode='exec')
        self.transpile_code(filename, ast_module)

    def transpile_code(self, filename, ast_module):
        "Transpile a code object into class files"
        # Convert the AST into Java opcodes
        if self.verbosity > 1:
            print('=' * 75)
            print(dump(ast_module))
            print('=' * 75)

        module = Visitor(self.namespace, filename, verbosity=self.verbosity).visit(ast_module)

        # Transpile the module code, adding any classfiles generated
        # to the list to be exported.
        self.classfiles.extend(module.transpile())
