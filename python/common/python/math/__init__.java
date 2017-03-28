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



public static double tofloatvalue(Object v){
	if(v instanceof Float){
		return ((Float)v).value;
	}
	else if (v instanceof Int) {
		return (new Float(((Int)v).value)).value;		
	} 
	else {
		throw new TypeError("a float is required");
	}
}


private static boolean exceptNaN(double result, double arg){
        if (Double.isNaN(result) && !Double.isNaN(arg)) {
            return true;
        } else {
            return false;
        }
    }

private static boolean exceptInf(double result, double arg) {
        if (Double.isInfinite(result) && !Double.isInfinite(arg)) {
            return true;
        } else {
            return false;
        }
}




@org.python.Method(
	__doc__="",
	args = {"v"}
)
  public static Object expm1(Object v) {
        
       double result = 0.0;
       result = Math.expm1(tofloatvalue(v));
       return (new Float(result));
    }

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object acos(Object v) {
		    Float r = new Float(Math.acos(tofloatvalue(v)));
		    if(Double.isNaN(r.value)){
	    		throw new ValueError("math domain error");
	    	}
	    	else{
	    		return r;
	    	}
	    }


@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object asin(Object v) {
        
	  		Float r = new Float(Math.asin(tofloatvalue(v)));
	    	if(Double.isNaN(r.value)){
	    		throw new ValueError("math domain error");
	    	}
	    	else{
	    		return r;
	    	}
  }


@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object atan(Object v) {

	    	Float r = new Float(Math.atan(tofloatvalue(v)));
	    	if(Double.isNaN(r.value)){
	    		throw new ValueError("math domain error");
	    	}
	    	else{
	    		return r;
	    	}
 }

@org.python.Method(
	__doc__="",
	args = {"v","w"}
)
public static Object atan2(Object v, Object w) {
        return new Float(Math.atan2(tofloatvalue(v), tofloatvalue(w)));
    }

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object ceil(Object v) {
        return new Int((int)Math.ceil(tofloatvalue(v)));
   }

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object cos(Object v) {
		double r = Math.cos(tofloatvalue(v));
        if(exceptNaN(r,tofloatvalue(v))) {
        	throw new ValueError("math domain error");
        }
        else {
        	return new Float(r);
        }

 }

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object exp(Object v) {
	  double r = Math.exp(tofloatvalue(v));
        if(exceptInf(r,tofloatvalue(v))) {
        	throw new ValueError("math domain error");
        }
        else{
        	return new Float(r);
        }
    }

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object floor(Object v) {
        return  new Int((int)Math.floor(tofloatvalue(v)));
 }

// @org.python.Method(
// 	__doc__="",
// 	args = {"v"}
// )
// public static Object log(Object v) {
//         return log(v, null);
//     }


@org.python.Method(
	__doc__="",
	args = {"v"},
	default_args = {"base"}
)
public static Object log(Object v, Object base) {
	double b = 0.0;
		if(base instanceof Float || base instanceof Int)
			 b = tofloatvalue(base);
		else if(base == null){
			 b = 0.0;
		}
        double doubleValue;
        doubleValue = log(tofloatvalue(v));
        return (b == 0.0) ? new Float(doubleValue)  : new Float(applyLoggedBase(doubleValue, b));
    }

private static double log(double v) {
        if (v <= 0.) {
            throw new ValueError("math domain error");
        } else {
            return Math.log(v);
        }
    }    


private static double applyLoggedBase(double loggedValue, double base) {

        double loggedBase;
        loggedBase = log(base);
        return loggedValue / loggedBase;
    }

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object log10(Object v) {
       if (tofloatvalue(v) <= 0.) {
           throw new ValueError("math domain error");
        } else {
            return new Float(Math.log10(tofloatvalue(v)));
        }
    }

@org.python.Method(
	__doc__="",
	args = {"v"}
)
public static Object log2(Object v) {
       if (tofloatvalue(v) <= 0.) {
           throw new ValueError("math domain error");
        } else {
            return log(v,new Float(2));
        }
    }

@org.python.Method(
	__doc__="",
	args = {"v","w"}
)
public static Object copysign(Object v, Object w) {
        return new Float(Math.copySign(tofloatvalue(v),tofloatvalue(w)));
    }


}
