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

public final class MassConversion {

    public static final double POUNDS_TO_OUNCES_RATIO = 16.0d;
    public static final double OUNCES_TO_POUNDS_RATIO = 1.0d
                                                        / POUNDS_TO_OUNCES_RATIO;
    public static final double METRIC_TONS_TO_POUNDS_RATIO = 2_204.623_240_2d;
    public static final double METRIC_TONS_TO_OUNCES_RATIO =
            METRIC_TONS_TO_POUNDS_RATIO * POUNDS_TO_OUNCES_RATIO;
    public static final double OUNCES_TO_METRIC_TONS_RATIO = 1.0d
                                                             / METRIC_TONS_TO_OUNCES_RATIO;
    public static final double KILOGRAMS_TO_OUNCES_RATIO = 0.001d
                                                           * METRIC_TONS_TO_OUNCES_RATIO;
    public static final double OUNCES_TO_KILOGRAMS_RATIO = 1.0d
                                                           / KILOGRAMS_TO_OUNCES_RATIO;
    public static final double GRAMS_TO_OUNCES_RATIO = 0.001d
                                                       * KILOGRAMS_TO_OUNCES_RATIO;
    public static final double OUNCES_TO_GRAMS_RATIO = 1.0d
                                                       / GRAMS_TO_OUNCES_RATIO;
    public static final double POUNDS_TO_METRIC_TONS_RATIO = 1.0d
                                                             / METRIC_TONS_TO_POUNDS_RATIO;
    public static final double KILOGRAMS_TO_POUNDS_RATIO = 0.001d
                                                           * METRIC_TONS_TO_POUNDS_RATIO;
    public static final double POUNDS_TO_KILOGRAMS_RATIO = 1.0d
                                                           / KILOGRAMS_TO_POUNDS_RATIO;
    public static final double GRAMS_TO_POUNDS_RATIO = 0.001d
                                                       * KILOGRAMS_TO_POUNDS_RATIO;
    public static final double POUNDS_TO_GRAMS_RATIO = 1.0d
                                                       / GRAMS_TO_POUNDS_RATIO;

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private MassConversion() {
    }

    // TODO: Use switch statements instead.
    public static double convertMass( final double mass,
                                      final MassUnit massUnitOld,
                                      final MassUnit massUnitNew ) {
        // If the units didn't change, preserve accuracy by avoiding redundant
        // conversion.
        if ( massUnitNew == massUnitOld ) {
            return mass;
        }

        double massConverted = mass;
        if ( massUnitOld == MassUnit.METRIC_TONS ) {
            if ( massUnitNew == MassUnit.KILOGRAMS ) {
                massConverted = mass * 1_000.0d;
            }
            else if ( massUnitNew == MassUnit.GRAMS ) {
                massConverted = mass * 1_000_000.0d;
            }
            else if ( massUnitNew == MassUnit.POUNDS ) {
                massConverted = metricTonsToPounds( mass );
            }
            else if ( massUnitNew == MassUnit.OUNCES ) {
                massConverted = metricTonsToOunces( mass );
            }
        }
        else if ( massUnitOld == MassUnit.KILOGRAMS ) {
            if ( massUnitNew == MassUnit.METRIC_TONS ) {
                massConverted = mass * 0.001d;
            }
            else if ( massUnitNew == MassUnit.GRAMS ) {
                massConverted = mass * 1_000.0d;
            }
            else if ( massUnitNew == MassUnit.POUNDS ) {
                massConverted = kilogramsToPounds( mass );
            }
            else if ( massUnitNew == MassUnit.OUNCES ) {
                massConverted = kilogramsToOunces( mass );
            }
        }
        else if ( massUnitOld == MassUnit.GRAMS ) {
            if ( massUnitNew == MassUnit.METRIC_TONS ) {
                massConverted = mass * 0.000_001d;
            }
            else if ( massUnitNew == MassUnit.KILOGRAMS ) {
                massConverted = mass * 0.001d;
            }
            else if ( massUnitNew == MassUnit.POUNDS ) {
                massConverted = gramsToPounds( mass );
            }
            else if ( massUnitNew == MassUnit.OUNCES ) {
                massConverted = gramsToOunces( mass );
            }
        }
        else if ( massUnitOld == MassUnit.POUNDS ) {
            if ( massUnitNew == MassUnit.METRIC_TONS ) {
                massConverted = poundsToMetricTons( mass );
            }
            else if ( massUnitNew == MassUnit.KILOGRAMS ) {
                massConverted = poundsToKilograms( mass );
            }
            else if ( massUnitNew == MassUnit.GRAMS ) {
                massConverted = poundsToGrams( mass );
            }
            else if ( massUnitNew == MassUnit.OUNCES ) {
                massConverted = poundsToOunces( mass );
            }
        }
        else if ( massUnitOld == MassUnit.OUNCES ) {
            if ( massUnitNew == MassUnit.METRIC_TONS ) {
                massConverted = ouncesToMetricTons( mass );
            }
            else if ( massUnitNew == MassUnit.KILOGRAMS ) {
                massConverted = ouncesToKilograms( mass );
            }
            else if ( massUnitNew == MassUnit.GRAMS ) {
                massConverted = ouncesToGrams( mass );
            }
            else if ( massUnitNew == MassUnit.POUNDS ) {
                massConverted = ouncesToPounds( mass );
            }
        }

        return massConverted;
    }

    public static double gramsToOunces( final double massGrams ) {
        return massGrams * GRAMS_TO_OUNCES_RATIO;
    }

    public static double gramsToPounds( final double massGrams ) {
        return massGrams * GRAMS_TO_POUNDS_RATIO;
    }

    public static double kilogramsToOunces( final double massKilograms ) {
        return massKilograms * KILOGRAMS_TO_OUNCES_RATIO;
    }

    public static double kilogramsToPounds( final double massKilograms ) {
        return massKilograms * KILOGRAMS_TO_POUNDS_RATIO;
    }

    public static double metricTonsToOunces( final double massMetricTons ) {
        return massMetricTons * METRIC_TONS_TO_OUNCES_RATIO;
    }

    public static double metricTonsToPounds( final double massMetricTons ) {
        return massMetricTons * METRIC_TONS_TO_POUNDS_RATIO;
    }

    public static double ouncesToGrams( final double massOunces ) {
        return massOunces * OUNCES_TO_GRAMS_RATIO;
    }

    public static double ouncesToKilograms( final double massOunces ) {
        return massOunces * OUNCES_TO_KILOGRAMS_RATIO;
    }

    public static double ouncesToMetricTons( final double massOunces ) {
        return massOunces * OUNCES_TO_METRIC_TONS_RATIO;
    }

    public static double ouncesToPounds( final double massOunces ) {
        return massOunces * OUNCES_TO_POUNDS_RATIO;
    }

    public static double poundsToGrams( final double massPounds ) {
        return massPounds * POUNDS_TO_GRAMS_RATIO;
    }

    public static double poundsToKilograms( final double massPounds ) {
        return massPounds * POUNDS_TO_KILOGRAMS_RATIO;
    }

    public static double poundsToMetricTons( final double massPounds ) {
        return massPounds * POUNDS_TO_METRIC_TONS_RATIO;
    }

    public static double poundsToOunces( final double massPounds ) {
        return massPounds * POUNDS_TO_OUNCES_RATIO;
    }
}
