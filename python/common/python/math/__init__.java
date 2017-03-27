package python.math;

import java.util.*;
import java.math.BigInteger;

//import org.python.types.ClassDictInit;
//import org.python.types.Py;
//import org.python.types.PyException;
//import org.python.types.PyFloat;
import org.python.types.Int;
import org.python.types.Float;
import org.python.Object;
import org.python.exceptions.ValueError;
import org.python.exceptions.TypeError;
//import org.python.types.PyTuple;
//import org.python.types.__builtin__;

@org.python.Module(

	__doc__="something"

)

public class __init__ extends org.python.types.Module{
	public __init__(){
	super();	
}
@org.python.Attribute
public static Object pi = new Float(Math.PI);
@org.python.Attribute
public static Object e = new Float(Math.E);

@org.python.Method(
	__doc__="",
	args = {"v"}
)
  public static Object expm1(Object v) {
        
        double result = 0.0;
        if (v instanceof Float) {
        result = Math.expm1(((Float)v).value);
    }
    else if(v instanceof Int){
      result = Math.expm1((new Float(((Int)v).value)).value);
    }
     return (new Float(result));
    }

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object acos(Object v) {
 

		    if(v instanceof Float){

		    	Float r = new Float(Math.acos(((Float)v).value));
		    	if(Double.isNaN(r.value)){
	    		throw new ValueError("math domain error");
	    	}
	    	else{
	    		return r;
	    	}
	    	}
	   	 else if(v instanceof Int) 
	  	  {
	  	  	Float r = new Float(Math.acos(((Int)v).value));
	  	  	if(Double.isNaN(r.value)){
	    		throw new ValueError("math domain error");
	    	}
	    	else{
	    		return r;
	    	}

	    }

		 
	    else {
	    	throw new TypeError("a float is required");
	    }
}

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object asin(Object v) {
        

	    if(v instanceof Float){
	    	Float r = new Float(Math.asin(((Float)v).value));
	    	if(Double.isNaN(r.value)){
	    		throw new ValueError("math domain error");
	    	}
	    	else{
	    		return r;
	    	}

	    }
	    else if(v instanceof Int) 
	    {
	    	Float r =  new Float(Math.asin(((Int)v).value));
	    	if(Double.isNaN(r.value)){
	    		throw new ValueError("math domain error");
	    	}
	    	else{
	    		return r;
	    	}

	    }
	    else {
	    	throw new TypeError("a float is required");
	    }
  }


@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object atan(Object v) {

        if(v instanceof Float){
	    	Float r = new Float(Math.atan(((Float)v).value));
	    	if(Double.isNaN(r.value)){
	    		throw new ValueError("math domain error");
	    	}
	    	else{
	    		return r;
	    	}

	    }
	    else if(v instanceof Int) 
	    {
	    	Float r =  new Float(Math.atan(((Int)v).value));
	    	if(Double.isNaN(r.value)){
	    		throw new ValueError("math domain error");
	    	}
	    	else{
	    		return r;
	    	}

	    }
	    else {
	    	throw new TypeError("a float is required");
	    }

    }

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object atan2(Object v, Object w) {

	if(!(v instanceof Float))
        if(v instanceof Float && w instanceof Float){
        return Math.atan2(v, w);
    }



}
