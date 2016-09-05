package net.bplaced.clayn.cfs.util;

/**
 * Basic types for file sizes that provide you with methods to transform them
 * from one unit to another.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 */
public enum SizeUnit
{

    /**
     * SizeUnit for byte values. Represents as {@code "b"}
     */
    BYTE("b")
    {
        @Override
        public double toByte(double val)
        {
            return val;
        }

        @Override
        public double toKiloByte(double val)
        {
            return val / MOD;
        }

        @Override
        public double toMegaByte(double val)
        {
            return toKiloByte(val) / MOD;
        }

        @Override
        public double toGigaByte(double val)
        {
            return toMegaByte(val) / MOD;
        }

        @Override
        public double toTerraByte(double val)
        {
            return toGigaByte(val) / MOD;
        }

        @Override
        public double toPetaByte(double val)
        {
            return toTerraByte(val) / MOD;
        }

        @Override
        public double toExaByte(double val)
        {
            return toPetaByte(val) / MOD;
        }

        @Override
        public double convert(double val, SizeUnit from)
        {
            return from.toByte(val);
        }

    },
    /**
     * SizeUnit for kilobyte values. Represents as {@code "kb"}
     */
    KILO_BYTE("kb")
    {
        @Override
        public double toByte(double val)
        {
            return val * MOD;
        }

        @Override
        public double toKiloByte(double val)
        {
            return val;
        }

        @Override
        public double toMegaByte(double val)
        {
            return toKiloByte(val) / MOD;
        }

        @Override
        public double toGigaByte(double val)
        {
            return toMegaByte(val) / MOD;
        }

        @Override
        public double toTerraByte(double val)
        {
            return toGigaByte(val) / MOD;
        }

        @Override
        public double toPetaByte(double val)
        {
            return toTerraByte(val) / MOD;
        }

        @Override
        public double toExaByte(double val)
        {
            return toPetaByte(val) / MOD;
        }

        @Override
        public double convert(double val, SizeUnit from)
        {
            return from.toKiloByte(val);
        }

    },
    /**
     * SizeUnit for megabyte values. Represents as {@code "Mb"}
     */
    MEGA_BYTE("Mb")
    {
        @Override
        public double toByte(double val)
        {
            return toKiloByte(val) * MOD;
        }

        @Override
        public double toKiloByte(double val)
        {
            return val * MOD;
        }

        @Override
        public double toMegaByte(double val)
        {
            return val;
        }

        @Override
        public double toGigaByte(double val)
        {
            return toMegaByte(val) / MOD;
        }

        @Override
        public double toTerraByte(double val)
        {
            return toGigaByte(val) / MOD;
        }

        @Override
        public double toPetaByte(double val)
        {
            return toTerraByte(val) / MOD;
        }

        @Override
        public double toExaByte(double val)
        {
            return toPetaByte(val) / MOD;
        }

        @Override
        public double convert(double val, SizeUnit from)
        {
            return from.toMegaByte(val);
        }

    },
    /**
     * SizeUnit for gigabyte values. Represents as {@code "Gb"}
     */
    GIGA_BYTE("Gb")
    {
        @Override
        public double toByte(double val)
        {
            return toKiloByte(val) * MOD;
        }

        @Override
        public double toKiloByte(double val)
        {
            return toMegaByte(val) * MOD;
        }

        @Override
        public double toMegaByte(double val)
        {
            return val * MOD;
        }

        @Override
        public double toGigaByte(double val)
        {
            return val;
        }

        @Override
        public double toTerraByte(double val)
        {
            return val / MOD;
        }

        @Override
        public double toPetaByte(double val)
        {
            return toTerraByte(val) / MOD;
        }

        @Override
        public double toExaByte(double val)
        {
            return toPetaByte(val) / MOD;
        }

        @Override
        public double convert(double val, SizeUnit from)
        {
            return from.toGigaByte(val);
        }

    },
    /**
     * SizeUnit for terrabyte values. Represents as {@code "Tb"}
     */
    TERRA_BYTE("Tb")
    {
        @Override
        public double toByte(double val)
        {
            return toKiloByte(val) * MOD;
        }

        @Override
        public double toKiloByte(double val)
        {
            return toMegaByte(val) * MOD;
        }

        @Override
        public double toMegaByte(double val)
        {
            return toGigaByte(val) * MOD;
        }

        @Override
        public double toGigaByte(double val)
        {
            return val * MOD;
        }

        @Override
        public double toTerraByte(double val)
        {
            return val;
        }

        @Override
        public double toPetaByte(double val)
        {
            return val / MOD;
        }

        @Override
        public double toExaByte(double val)
        {
            return toPetaByte(val) / MOD;
        }

        @Override
        public double convert(double val, SizeUnit from)
        {
            return from.toTerraByte(val);
        }

    },
    /**
     * SizeUnit for petabyte values. Represents as {@code "Pb"}
     */
    PETA_BYTE("Pb")
    {
        @Override
        public double toByte(double val)
        {
            return toKiloByte(val) * MOD;
        }

        @Override
        public double toKiloByte(double val)
        {
            return toMegaByte(val) * MOD;
        }

        @Override
        public double toMegaByte(double val)
        {
            return toGigaByte(val) * MOD;
        }

        @Override
        public double toGigaByte(double val)
        {
            return toTerraByte(val) * MOD;
        }

        @Override
        public double toTerraByte(double val)
        {
            return val * MOD;
        }

        @Override
        public double toPetaByte(double val)
        {
            return val;
        }

        @Override
        public double toExaByte(double val)
        {
            return val / MOD;
        }

        @Override
        public double convert(double val, SizeUnit from)
        {
            return from.toPetaByte(val);
        }

    },
    /**
     * SizeUnit for exabyte values. Represents as {@code "Eb"}
     */
    EXA_BYTE("Eb")
    {
        @Override
        public double toByte(double val)
        {
            return toKiloByte(val) * MOD;
        }

        @Override
        public double toKiloByte(double val)
        {
            return toMegaByte(val) * MOD;
        }

        @Override
        public double toMegaByte(double val)
        {
            return toGigaByte(val) * MOD;
        }

        @Override
        public double toGigaByte(double val)
        {
            return toTerraByte(val) * MOD;
        }

        @Override
        public double toTerraByte(double val)
        {
            return toPetaByte(val) * MOD;
        }

        @Override
        public double toPetaByte(double val)
        {
            return val * MOD;
        }

        @Override
        public double toExaByte(double val)
        {
            return MOD;
        }

        @Override
        public double convert(double val, SizeUnit from)
        {
            return from.toExaByte(val);
        }
    };

    private SizeUnit(String shortString)
    {
        this.shortString = shortString;
    }

    private final String shortString;

    /**
     * Converts the given value to a {@link #BYTE byte} value. The value gets
     * interpreted as a value in this unit.
     *
     * @param val the value to convert
     * @return the given value converted from this unit to {@link #BYTE byte}
     */
    public abstract double toByte(double val);

    /**
     * Converts the given value to a {@link #KILO_BYTE kilobyte} value. The
     * value gets interpreted as a value in this unit.
     *
     * @param val the value to convert
     * @return the given value converted from this unit to
     * {@link #KILO_BYTE kilobyte}
     */
    public abstract double toKiloByte(double val);

    /**
     * Converts the given value to a {@link #MEGA_BYTE megabyte} value. The
     * value gets interpreted as a value in this unit.
     *
     * @param val the value to convert
     * @return the given value converted from this unit to
     * {@link #MEGA_BYTE megabyte}
     */
    public abstract double toMegaByte(double val);

    /**
     * Converts the given value to a {@link #GIGA_BYTE giabyte} value. The value
     * gets interpreted as a value in this unit.
     *
     * @param val the value to convert
     * @return the given value converted from this unit to
     * {@link #GIGA_BYTE gigabyte}
     */
    public abstract double toGigaByte(double val);

    /**
     * Converts the given value to a {@link #TERRA_BYTE terrabyte} value. The
     * value gets interpreted as a value in this unit.
     *
     * @param val the value to convert
     * @return the given value converted from this unit to
     * {@link #TERRA_BYTE terrabyte}
     */
    public abstract double toTerraByte(double val);

    /**
     * Converts the given value to a {@link #PETA_BYTE petabyte} value. The
     * value gets interpreted as a value in this unit.
     *
     * @param val the value to convert
     * @return the given value converted from this unit to
     * {@link #PETA_BYTE petabyte}
     */
    public abstract double toPetaByte(double val);

    /**
     * Converts the given value to a {@link #EXA_BYTE exabyte} value. The value
     * gets interpreted as a value in this unit.
     *
     * @param val the value to convert
     * @return the given value converted from this unit to
     * {@link #EXA_BYTE exabyte}
     */
    public abstract double toExaByte(double val);

    /**
     * Converts the given value from the given {@link SizeUnit unit} to this
     * one.
     *
     * @param val the value to convert
     * @param from the unit the value gets converted from
     * @return the value converted to this unit
     */
    public abstract double convert(double val, SizeUnit from);

    @Override
    public String toString()
    {
        return shortString;
    }

    /**
     * Returns a representation of the given value for this unit. This method
     * returns a string that contains the given value followed by the string
     * representation of this unit.
     *
     * @param val the value to convert
     * @return a string that contains the value together with the string
     * representation of this unit
     */
    public String toString(double val)
    {
        return String.valueOf(val) + ' ' + toString();
    }

    private static final double MOD = 1000.0;
    
}
