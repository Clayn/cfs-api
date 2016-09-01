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

    public double toByte(double val)
    {
        return this.convert(val, BYTE);
    }

    public double toKiloByte(double val)
    {
        throw new AbstractMethodError();
    }

    public double toMegaByte(double val)
    {
        throw new AbstractMethodError();
    }

    public double toGigaByte(double val)
    {
        throw new AbstractMethodError();
    }

    public double toTerraByte(double val)
    {
        throw new AbstractMethodError();
    }

    public double toPetaByte(double val)
    {
        throw new AbstractMethodError();
    }

    public double toExaByte(double val)
    {
        throw new AbstractMethodError();
    }

    public double convert(double val, SizeUnit from)
    {
        throw new AbstractMethodError();
    }

    @Override
    public String toString()
    {
        return shortString;
    }

    public String toString(double val)
    {
        return String.valueOf(val) + ' ' + toString();
    }

    private static final double MOD = 1000.0;

}
