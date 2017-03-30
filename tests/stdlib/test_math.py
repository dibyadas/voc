from ..utils import TranspileTestCase


class MathTests(TranspileTestCase):
    def test_acos(self):
        self.assertCodeExecution("""
            try:
                x = math.acos(23)
            except ValueError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.acos(0.3434)
            print(x)
            """)
        self.assertCodeExecution("""
            try:
                x = math.acos("ss")
            except TypeError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.acos(-0.24)
            """)

    def test_acosh(self):
        self.assertCodeExecution("""
            try:
                x = math.acosh(-23)
            except ValueError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.acosh(12)
            """)
        self.assertCodeExecution("""
            try:
                x = math.acosh("ss")
            except TypeError as err:
                print(err)
            """)

    def test_asin(self):
        self.assertCodeExecution("""
            try:
                x = math.asin(23)
            except ValueError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.asin(0.3434)
            print(x)
            """)
        self.assertCodeExecution("""
            try:
                x = math.asin("ss")
            except TypeError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.asin(-0.24)
            """)

    def test_asinh(self):
        self.assertCodeExecution("""
            try:
                x = math.asinh(-23)
            except ValueError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.asinh(12)
            """)
        self.assertCodeExecution("""
            try:
                x = math.asinh("ss")
            except TypeError as err:
                print(err)
            """)

    def test_atan(self):
        self.assertCodeExecution("""
            try:
                x = math.atan("ss")
            except TypeError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.atan(-0.4524)
            """)

    def test_atan2(self):
        self.assertCodeExecution("""
            try:
                x = math.atan2("ss")
            except TypeError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.atan2(10,35453535)
            """)

    def test_atanh(self):
        self.assertCodeExecution("""
            try:
                x = math.atan("ss")
            except TypeError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.atan(-0.4524)
            """)
        self.assertCodeExecution("""
            try:
                x = math.atanh(-23)
            except ValueError as err:
                print(err)
            """)

    def test_ceil(self):
        self.assertCodeExecution("""
            try:
                x = math.ceil("ss")
            except TypeError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.ceil(-0.4524)
            """)

    def test_copysign(self):
        self.assertCodeExecution("""
            try:
                x = math.copysign("ss",34)
            except TypeError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            try:
                x = math.copysign(34,"ss")
            except TypeError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            try:
                x = math.copysign("d","ss")
            except TypeError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            x = math.copysign(2,-0.4524)
            """)

    def test_cos(self):
        self.assertCodeExecution("""
            x = math.cos(23345)
            """)
        self.assertCodeExecution("""
            try:
                x = math.cos("ddf")
            except TypeError as err:
                print(err)
            """)

    def test_cosh(self):
        self.assertCodeExecution("""
            try:
                x = math.cosh(23345)
            except OverflowError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            try:
                x = math.cosh("ddf")
            except TypeError as err:
                print(err)
            """)

        self.assertCodeExecution("""
            x = math.cosh(25)
            """)

    def test_sinh(self):
        self.assertCodeExecution("""
            try:
                x = math.sinh(23345)
            except OverflowError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            try:
                x = math.sinh("ddf")
            except TypeError as err:
                print(err)
            """)

        self.assertCodeExecution("""
            x = math.sinh(25)
            """)

    def test_tanh(self):
        self.assertCodeExecution("""
            try:
                x = math.tanh(23345)
            except OverflowError as err:
                print(err)
            """)
        self.assertCodeExecution("""
            try:
                x = math.tanh("ddf")
            except TypeError as err:
                print(err)
            """)

        self.assertCodeExecution("""
            x = math.tanh(25)
            """)
