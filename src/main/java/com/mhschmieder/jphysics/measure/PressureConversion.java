/*
 * MIT License
 *
 * Copyright (c) 2020, 2026 Mark Schmieder. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of the jphysics Library
 *
 * You should have received a copy of the MIT License along with the jphysics
 * Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/jphysics
 */
package com.mhschmieder.jphysics.measure;

public final class PressureConversion {

    // ////////////////////////////////////////////////////////////////////////
    // The following is based on "Scientific Unit Conversion" 2nd Ed. by
    // Francois Cardarelli, Springer.
    // TODO: Consider splitting these out into a ConversionFactors interface,
    //  implemented by this class.
    // NOTE: The drawback to this approach is making them private, inaccessible
    //  for tight-loop usage or for usage as scale factors in an affine
    //  transform matrix. This needs further investigation.
    public static final double ATMOSPHERES_TO_PASCALS_RATIO = 101_325.0d;
    public static final double PASCALS_TO_ATMOSPHERES_RATIO = 1.0d
                                                              / ATMOSPHERES_TO_PASCALS_RATIO;
    public static final double KILOPASCALS_TO_MILLIBARS_RATIO = 10.0d;
    public static final double KILOPASCALS_TO_PASCALS_RATIO = 1_000.0d;
    public static final double PASCALS_TO_KILOPASCALS_RATIO = 1.0d
                                                              / KILOPASCALS_TO_PASCALS_RATIO;
    public static final double ATMOSPHERES_TO_KILOPASCALS_RATIO =
            ATMOSPHERES_TO_PASCALS_RATIO * PASCALS_TO_KILOPASCALS_RATIO;
    public static final double KILOPASCALS_TO_ATMOSPHERES_RATIO = 1.0d
                                                                  / ATMOSPHERES_TO_KILOPASCALS_RATIO;
    public static final double MILLIBARS_TO_KILOPASCALS_RATIO = 1.0d
                                                                / KILOPASCALS_TO_MILLIBARS_RATIO;
    public static final double MILLIBARS_TO_PASCALS_RATIO =
            MILLIBARS_TO_KILOPASCALS_RATIO * KILOPASCALS_TO_PASCALS_RATIO;
    public static final double PASCALS_TO_MILLIBARS_RATIO = 1.0d
                                                            / MILLIBARS_TO_PASCALS_RATIO;
    public static final double ATMOSPHERES_TO_MILLIBARS_RATIO =
            ATMOSPHERES_TO_PASCALS_RATIO * PASCALS_TO_MILLIBARS_RATIO;
    public static final double MILLIBARS_TO_ATMOSPHERES_RATIO = 1.0d
                                                                / ATMOSPHERES_TO_MILLIBARS_RATIO;

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private PressureConversion() {
    }

    // TODO: Use switch statements instead.
    public static double convertPressure( final double pressure,
                                          final PressureUnit pressureUnitOld,
                                          final PressureUnit pressureUnitNew ) {
        // If the units didn't change, preserve accuracy by avoiding redundant
        // conversion.
        if ( pressureUnitNew == pressureUnitOld ) {
            return pressure;
        }

        double pressureConverted = pressure;
        if ( pressureUnitOld == PressureUnit.MILLIBARS ) {
            if ( pressureUnitNew == PressureUnit.PASCALS ) {
                pressureConverted = millibarsToPascals( pressure );
            }
            else if ( pressureUnitNew == PressureUnit.KILOPASCALS ) {
                pressureConverted = millibarsToKilopascals( pressure );
            }
            else if ( pressureUnitNew == PressureUnit.ATMOSPHERES ) {
                pressureConverted = millibarsToAtmospheres( pressure );
            }
        }
        else if ( pressureUnitOld == PressureUnit.ATMOSPHERES ) {
            if ( pressureUnitNew == PressureUnit.MILLIBARS ) {
                pressureConverted = atmospheresToMillibars( pressure );
            }
            else if ( pressureUnitNew == PressureUnit.PASCALS ) {
                pressureConverted = atmospheresToPascals( pressure );
            }
            else if ( pressureUnitNew == PressureUnit.KILOPASCALS ) {
                pressureConverted = atmospheresToKilopascals( pressure );
            }
        }
        else if ( pressureUnitOld == PressureUnit.PASCALS ) {
            if ( pressureUnitNew == PressureUnit.MILLIBARS ) {
                pressureConverted = pascalsToMillibars( pressure );
            }
            else if ( pressureUnitNew == PressureUnit.KILOPASCALS ) {
                pressureConverted = pressure * 0.001d;
            }
            else if ( pressureUnitNew == PressureUnit.ATMOSPHERES ) {
                pressureConverted = pascalsToAtmospheres( pressure );
            }
        }
        else if ( pressureUnitOld == PressureUnit.KILOPASCALS ) {
            if ( pressureUnitNew == PressureUnit.MILLIBARS ) {
                pressureConverted = kilopascalsToMillibars( pressure );
            }
            else if ( pressureUnitNew == PressureUnit.PASCALS ) {
                pressureConverted = pressure * 1_000.0d;
            }
            else if ( pressureUnitNew == PressureUnit.ATMOSPHERES ) {
                pressureConverted = kilopascalsToAtmospheres( pressure );
            }
        }

        return pressureConverted;
    }

    public static double atmospheresToKilopascals( final double pressureAtmopsheres ) {
        return pressureAtmopsheres * ATMOSPHERES_TO_KILOPASCALS_RATIO;
    }

    public static double atmospheresToMillibars( final double pressureAtmopsheres ) {
        return pressureAtmopsheres * ATMOSPHERES_TO_MILLIBARS_RATIO;
    }

    public static double atmospheresToPascals( final double pressureAtmopsheres ) {
        return pressureAtmopsheres * ATMOSPHERES_TO_PASCALS_RATIO;
    }

    public static double kilopascalsToAtmospheres( final double pressureKilopascals ) {
        return pressureKilopascals * KILOPASCALS_TO_ATMOSPHERES_RATIO;
    }

    public static double kilopascalsToMillibars( final double pressureKilopascals ) {
        return pressureKilopascals * KILOPASCALS_TO_MILLIBARS_RATIO;
    }

    public static double millibarsToAtmospheres( final double pressureMillibars ) {
        return pressureMillibars * MILLIBARS_TO_ATMOSPHERES_RATIO;
    }

    public static double millibarsToKilopascals( final double pressureMillibars ) {
        return pressureMillibars * MILLIBARS_TO_KILOPASCALS_RATIO;
    }

    public static double millibarsToPascals( final double pressureMillibars ) {
        return pressureMillibars * MILLIBARS_TO_PASCALS_RATIO;
    }

    public static double pascalsToAtmospheres( final double pressurePascals ) {
        return pressurePascals * PASCALS_TO_ATMOSPHERES_RATIO;
    }

    public static double pascalsToMillibars( final double pressurePascals ) {
        return pressurePascals * PASCALS_TO_MILLIBARS_RATIO;
    }

    public static double kilopascalsToPascals( final double pressureKilopascals ) {
        return pressureKilopascals * KILOPASCALS_TO_PASCALS_RATIO;
    }

    public static double pascalsToKilopascals( final double pressurePa ) {
        return pressurePa * PASCALS_TO_KILOPASCALS_RATIO;
    }
}
