/*
 * Copyright (C) 2016 Clayn <clayn_osmato@gmx.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.bplaced.clayn.cfs.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            return val;
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

    public String toString(long val)
    {
        return String.valueOf(val) + ' ' + toString();
    }

    private static final double MOD = 1000.0;

    /**
     * Creates a string from the given byte value that is readable by humans. 
     * This method only shows decimal values. E.g. {@code 1030} will be 
     * represented as {@code 1 kb 30 b} and {@code 4015032} as {@code 4 Mb 15 kb 32b} 
     * @param bVal the byte value to convert
     * @return a human readable representation of the given byte value
     */
    public static String toReadableString(long bVal)
    {
        if (bVal < 0)
        {
            throw new IllegalArgumentException("Byte value must be >=0");
        }
        if (bVal == 0)
        {
            return BYTE.toString(bVal);
        }
        Map<SizeUnit, String> parts = new HashMap<>();
        List<SizeUnit> units = Arrays.asList(KILO_BYTE, MEGA_BYTE, GIGA_BYTE,
                TERRA_BYTE, PETA_BYTE, EXA_BYTE);
        Comparator<SizeUnit> UNIT_ASC = Comparator.comparingInt(
                SizeUnit::ordinal).reversed();
        Collections.sort(units, UNIT_ASC);

        for (SizeUnit unit : units)
        {

            int num = (int) unit.convert(bVal, BYTE);
            if (num >= 1)
            {
                parts.put(unit, unit.toString(num));
                bVal -= unit.toByte(num);
            }
        }

        if (bVal > 0)
        {
            parts.put(BYTE, BYTE.toString(bVal));
        }
        StringBuilder builder = new StringBuilder(parts.size() * 5);
        parts.keySet().stream().sorted(UNIT_ASC)
                .map(parts::get).map(" "::concat).forEach(builder::append);
        return builder.toString().trim();
    }
}
