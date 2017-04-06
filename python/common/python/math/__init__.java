package python.math;

import java.math.BigInteger;
import java.math.BigDecimal;
import org.python.types.Int;
import org.python.types.Float;
import org.python.types.Tuple;
import org.python.types.List;
import org.python.types.Bool;
import python.errorfunction.math_erf;
import python.gammafunction.math_gamma;
import org.python.Object;
import org.python.exceptions.ValueError;
import org.python.exceptions.TypeError;
import org.python.exceptions.OverflowError;

@org.python.Module(
        __doc__ = ""
)
public class __init__ extends org.python.types.Module {
    public __init__() {
        super();
    }

    @org.python.Attribute
    public static Object pi = new Float(Math.PI);
    @org.python.Attribute
    public static Object e = new Float(Math.E);
    // @org.python.Attribute
    // public static Object inf = new Float(Double.POSITIVE_INFINITY);
    // @org.python.Attribute
    // public static Object nan = new Float(Double.NaN);



    public static double tofloatvalue(Object v) {
        if (v instanceof Float) {
            return ((Float) v).value;
        } else if (v instanceof Int) {
            return (new Float(((Int) v).value)).value;
        } else {
            if (org.Python.VERSION < 0x03060000) {
                throw new TypeError("a float is required");
            } else {
                throw new TypeError("must be real number, not " + v.typeName());
            }
        }
    }



    private static boolean exceptNaN(double result, double arg) {
        return (Double.isNaN(result) && !Double.isNaN(arg));
    }

    private static boolean exceptInf(double result, double arg) {
        return (Double.isInfinite(result) && !Double.isInfinite(arg));
    }

    private static boolean isIntegral(double v) {
        return Math.ceil(v) - v == 0;
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object erf(Object v) {
        double result = math_erf.erf(tofloatvalue(v));
        return new Float(result);
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object erfc(Object v) {
        double result = math_erf.erfc(tofloatvalue(v));
        return new Float(result);
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object gamma(Object v) {
        double result = math_gamma.gamma(tofloatvalue(v));
        return new Float(result);
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object lgamma(Object v) {
        double result = math_gamma.lgamma(tofloatvalue(v));
        return new Float(result);
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object expm1(Object v) {

        double result = 0.0;
        result = Math.expm1(tofloatvalue(v));
        if (Double.isInfinite(result)) {
            throw new OverflowError("math range error");
        } else {
            return new Float(result);
        }
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object acos(Object v) {
        Float r = new Float(Math.acos(tofloatvalue(v)));
        if (Double.isNaN(r.value)) {
            throw new ValueError("math domain error");
        } else {
            return r;
        }
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object cos(Object arg) {
        double v = tofloatvalue(arg);
        if (exceptNaN(Math.cos(v), v)) {
            throw new ValueError("math domain error");
        } else {
            return new Float(Math.cos(v));
        }
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object acosh(Object v) {
        double y = tofloatvalue(v);
        if (y < 1.0) {
            throw new ValueError("math domain error");

        } else {
            if (y < 2.) {
                final double u = y - 1.;
                double s = Math.sqrt(u * (2. + u));
                return new Float(Math.log1p(u + s));

            } else if (y < 0x1p27) {
                final double u = 1. / y;
                double t = Math.sqrt((1. + u) * (1. - u));
                return new Float(Math.log(y * (1. + t)));

            } else {
                return new Float(Math.log(y) + Math.log1p(2));
            }
        }
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object asin(Object v) {

        Float r = new Float(Math.asin(tofloatvalue(v)));
        if (Double.isNaN(r.value)) {
            throw new ValueError("math domain error");
        } else {
            return r;
        }
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object asinh(Object arg) {
        double v = tofloatvalue(arg);
        if (isnan(v) || isinf(v)) {
            return arg;
        }

        final double ln2 = 6.93147180559945286227e-01;
        final double large = 1 << 28;
        final double small = 1.0 / (1 << 28);
        boolean sign = false;

        if (v < 0) {
            v = -v;
            sign = true;
        }

        double temp;
        if (v > large) {
            temp = log(v) + ln2;
        } else if (v > 2) {
            temp = log(2 * v + 1 / (Math.sqrt(v * v + 1) + v));
        } else if (v < small) {
            temp = v;
        } else {
            temp = Math.log1p(v + v * v / (1 + Math.sqrt(1 + v * v)));
        }

        return new Float(sign ? -temp : temp);
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object sin(Object arg) {
        double v = tofloatvalue(arg);
        if (exceptNaN(Math.sin(v), v)) {
            throw new ValueError("math domain error");
        } else {
            return new Float(Math.sin(v));
        }
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object atan(Object v) {

        Float r = new Float(Math.atan(tofloatvalue(v)));
        if (Double.isNaN(r.value)) {
            throw new ValueError("math domain error");
        } else {
            return r;
        }
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v",
            "w"
            }
    )
    public static Object atan2(Object v, Object w) {
        return new Float(Math.atan(tofloatvalue(v) / tofloatvalue(w)));
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object atanh(Object v) {
        double absy = Math.abs(tofloatvalue(v));
        if (absy >= 1.0) {
            throw new ValueError("math domain error");
        } else {
            double u = (absy + absy) / (1. - absy);
            double x = 0.5 * Math.log1p(u);
            return new Float(Math.copySign(x, tofloatvalue(v)));
        }
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object tan(Object arg) {
        double v = tofloatvalue(arg);
        if (exceptNaN(Math.tan(v), v)) {
            throw new ValueError("math domain error");
        } else {
            return new Float(Math.tan(v));
        }
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object fsum(Object v) {
        if (v instanceof org.python.types.Str) {
            tofloatvalue(v);
        }
        if (!(v instanceof Tuple) && !(v instanceof List)) {
            throw new TypeError("'" + v.typeName() + "' object is not iterable");
        } else {
            java.util.List l;
            if (v instanceof Tuple) {
                l = new java.util.ArrayList<Object>(((Tuple) v).value);
            } else {
                l = new java.util.ArrayList<Object>(((List) v).value);
            }
            BigDecimal sum = new BigDecimal(0);
            for (int i = 0; i < l.size(); i++) {
                BigDecimal t = new BigDecimal(tofloatvalue(((Object) l.get(i))));
                sum = sum.add(t);
            }
            return new Float(sum.doubleValue());
        }
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object ceil(Object v) {
        return new Int((int) Math.ceil(tofloatvalue(v)));
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object cosh(Object arg) {
        double v = tofloatvalue(arg);
        if (exceptInf(Math.cosh(v), v)) {
            throw new OverflowError("math range error");
        }
        return new Float(Math.cosh(v));
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object sinh(Object arg) {
        double v = tofloatvalue(arg);
        if (exceptInf(Math.sinh(v), v)) {
            throw new OverflowError("math range error");
        }
        return new Float(Math.sinh(v));
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object tanh(Object arg) {
        double v = tofloatvalue(arg);
        if (exceptInf(Math.tanh(v), v)) {
            throw new OverflowError("math range error");
        }
        return new Float(Math.tanh(v));
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object sqrt(Object arg) {
        double v = tofloatvalue(arg);
        if (exceptNaN(Math.sqrt(v), v)) {
            throw new ValueError("math domain error");
        } else {
            return new Float(Math.sqrt(v));
        }
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "arg1",
            "arg2"
            }
    )
    public static Object gcd(Object arg1, Object arg2) {
        if (org.Python.VERSION < 0x03050000) {
            throw new org.python.exceptions.AttributeError("'module' object has no attribute 'gcd'");
        } else {
            if (!(arg1 instanceof Int) && !(arg2 instanceof Int)) {
                throw new TypeError("'" + ((org.python.types.Object) arg1).__class__.PYTHON_TYPE_NAME + " object cannot be interpreted as an integer");
            } else {
                long m = Math.abs(((Int) arg1).value);
                long n = Math.abs(((Int) arg2).value);
                long h = 1;
                long p = m * n;
                for (long i = 2; i < p; i++) {
                    if ((m % i == 0) && (n % i == 0)) {
                        h = i;
                    }
                }
                return new Int(h);
            }
        }
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object fabs(Object v) {
        return new Float(Math.abs(tofloatvalue(v)));
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "a1",
            "a2"
            }
    )
    public static Object fmod(Object a1, Object a2) {
        double v = tofloatvalue(a1);
        double w = tofloatvalue(a2);
        if (isnan(v) || isnan(w)) {
            return new Float(Double.NaN);
        }
        if (isinf(w)) {
            return a1;
        }
        if (w == 0.0) {
            throw new ValueError("math domain error");
        }
        if (isinf(v) && w == 0.0) {
            throw new ValueError("math domain error");
        }
        return new Float(v % w);
    }



    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object exp(Object v) {
        double r = Math.exp(tofloatvalue(v));
        if (exceptInf(r, tofloatvalue(v))) {
            throw new OverflowError("math range error");
        } else {
            return new Float(r);
        }
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object floor(Object v) {
        return new Int((int) Math.floor(tofloatvalue(v)));
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            },
            default_args = {
            "base"
            }
    )
    public static Object log(Object v, Object base) {
        double b = 0.0;
        if (base instanceof Float || base instanceof Int) {
            b = tofloatvalue(base);
        } else if (base == null) {
            b = 0.0;
        }
        double doubleValue;
        doubleValue = log(tofloatvalue(v));
        return (b == 0.0) ? new Float(doubleValue) : new Float(applyLoggedBase(doubleValue, b));
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
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object log10(Object v) {
        if (tofloatvalue(v) <= 0.) {
            throw new ValueError("math domain error");
        } else {
            return new Float(Math.log10(tofloatvalue(v)));
        }
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object log2(Object v) {
        if (tofloatvalue(v) <= 0.) {
            throw new ValueError("math domain error");
        } else {
            return log(v, new Float(2));
        }
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object log1p(Object v) {
        if (tofloatvalue(v) <= -1.) {
            throw new ValueError("math domain error");
        } else {
            return new Float(Math.log1p(tofloatvalue(v)));
        }
    }



    @org.python.Method(
            __doc__ = "",
            args = {
            "v",
            "w"
            }
    )
    public static Object copysign(Object v, Object w) {
        return new Float(Math.copySign(tofloatvalue(v), tofloatvalue(w)));
    }



    public static boolean isnan(double v) {
        return Double.isNaN(v);
    }

    public static boolean isinf(double v) {
        return Double.isInfinite(v);
    }

    private static boolean isninf(double v) {
        return v == Double.NEGATIVE_INFINITY;
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object modf(Object v) {
        double r = tofloatvalue(v);
        if (isnan(r)) {
            java.util.List temp = new java.util.ArrayList<Object>();
            temp.add(new Float(r));
            temp.add(new Float(r));
            return new Tuple(temp);
        }
        if (isinf(r)) {
            double first = 0.0;
            if (isninf(r)) {
                first = -0.0;
            }
            java.util.List temp = new java.util.ArrayList<Object>();
            temp.add(new Float(first));
            temp.add(new Float(r));
            return new Tuple(temp);
        }
        double w = r % (1.0);
        r -= w;
        java.util.List temp = new java.util.ArrayList<Object>();
        temp.add(new Float(w));
        if (r == -0d) {
            temp.add(new Float(-r));
        } else {
            temp.add(new Float(r));
        }
        return new Tuple(temp);
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object frexp(Object v) {
        int exponent;
        double mantissa;
        double x = tofloatvalue(v);
        exponent = Math.getExponent(x);
        switch (exponent) {

            default:
                exponent = exponent + 1;
                mantissa = Math.scalb(x, -exponent);
                break;

            case 1024:
                mantissa = x;
                exponent = 0;
                break;

            case -1023:
                if (x == 0.) {
                    mantissa = x;
                    exponent = 0;
                } else {
                    exponent = Math.getExponent(x * 0x1p52) - 51;
                    mantissa = Math.scalb(x, -exponent);
                }
                break;
        }
        java.util.List temp = new java.util.ArrayList<Object>();
        temp.add(new Float(mantissa));
        temp.add(new Float(exponent));
        return new Tuple(temp);
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v",
            "wObj"
            }
    )
    public static Object ldexp(Object v, Object wObj) {
        double r = tofloatvalue(v);
        double w = 0.0;
        if (!(wObj instanceof Int)) {
            throw new TypeError("Expected an int as second argument to ldexp.");
        } else {
            w = tofloatvalue(wObj);
        }
        if (w < Integer.MIN_VALUE) {
            w = Integer.MIN_VALUE;
        } else if (w > Integer.MAX_VALUE) {
            w = Integer.MAX_VALUE;
        }
        double result = Math.scalb(r, (int) w);
        if (exceptInf(result, r)) {
            throw new OverflowError("math range error");
        } else {
            return new Float(result);
        }

    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v",
            "w"
            }
    )
    public static Object hypot(Object v, Object w) {
        double x = tofloatvalue(v);
        double y = tofloatvalue(w);
        double mag = Math.hypot(x, y);
        if (Double.isInfinite(mag) && !(Double.isInfinite(x) || Double.isInfinite(y))) {
            throw new OverflowError("math range error");
        }
        return new Float(mag);
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object radians(Object v) {
        return new Float(Math.toRadians(tofloatvalue(v)));
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object degrees(Object v) {
        return new Float(Math.toDegrees(tofloatvalue(v)));
    }



    @org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object factorial(Object arg) {
        double v = 0.0;
        if (arg instanceof Float) {
            v = ((Float) arg).value;
        } else if (arg instanceof Int) {
            v = (new Float(((Int) arg).value)).value;
        } else {
            throw new TypeError("an integer is required");
        }
        if ((v - ((int) v)) != 0.0) {
            throw new ValueError("factorial() only accepts integral values");
        } else if (v <= 0.0) {
            throw new ValueError("factorial() not defined for negative values");
        } else if (v == 0.0 || v == 1.0) {
            return new Float(1);
        } else if (v < 0.0 || isnan(v) || isinf(v)) {
            throw new ValueError("math domain error");
        } else if (!isIntegral(v)) {
            throw new ValueError("math domain error");
        } else {
            long value = (long) v;
            BigInteger bi = new BigInteger(Long.toString(value));
            for (long l = value - 1; l > 1; l--) {
                bi = bi.multiply(new BigInteger(Long.toString(l)));
            }
            return new Int(bi.intValue());
        }
    }

    /*@org.python.Method(
            __doc__ = "",
            args = {
            "arg"
            }
    )
    public static Object trunc(Object arg) {
        return arg.__trunc__();
    }*/





    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object isnan(Object v) {
        return new Bool(Double.isNaN(tofloatvalue(v)));
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object isinf(Object v) {
        return new Bool(Double.isInfinite(tofloatvalue(v)));
    }

    @org.python.Method(
            __doc__ = "",
            args = {
            "v"
            }
    )
    public static Object isfinite(Object v) {
        return new Bool(!(Double.isInfinite(tofloatvalue(v))));
    }


    @org.python.Method(
            __doc__ = "",
            args = {
            "a1",
            "a2"
            }
    )
    public static Object pow(Object a1, Object a2) {
        double v = tofloatvalue(a1);
        double w = tofloatvalue(a2);
        if (w == 0.0) {
            return new Float(1);
        }
        if (v == 1.0) {
            return a1;
        }
        if (isnan(v) || isnan(w)) {
            return new Float(Double.NaN);
        }
        if (v == 0.0) {
            if (w == 0.0) {
                return new Float(1);
            } else if (w > 0.0 || w == Double.POSITIVE_INFINITY) {
                return new Float(0.0);
            } else {
                throw new ValueError("math domain error");
            }
        }
        if (v == Double.NEGATIVE_INFINITY) {
            if (w == Double.NEGATIVE_INFINITY) {
                return new Float(0);
            }
            if (isinf(w)) {
                return new Float(Double.POSITIVE_INFINITY);
            }
            if (w == 0.0) {
                return new Float(1);
            }
            if (w > 0.0) {
                if (isOdd(w)) {
                    return new Float(Double.NEGATIVE_INFINITY);
                }
                return new Float(Double.POSITIVE_INFINITY);
            }
            if (isOdd(w)) {
                return new Float(-0.0);
            }
            return new Float(0.0);
        }
        if (w == Double.NEGATIVE_INFINITY) {
            if (v < 0.0) {
                if (v == -1.0) {
                    return new Float(1.0);
                }
                if (v < -1.0) {
                    return new Float(0.0);
                }
                return new Float(Double.POSITIVE_INFINITY);
            }
        }
        if (w == Double.POSITIVE_INFINITY) {
            if (v < 0.0) {
                if (v == -1.0) {
                    return new Float(1.0);
                }
                if (v < -1.0) {
                    return new Float(Double.POSITIVE_INFINITY);
                }
                return new Float(0.0);
            }
        }
        if (v < 0.0 && !isIntegral(w)) {
            throw new ValueError("math domain error");
        }
        return new Float(Math.pow(v, w));
    }

    private static boolean isOdd(double v) {
        return isIntegral(v) && v % 2 != 0.0;
    }
}
