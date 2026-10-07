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

public final class DistanceConversion {

    // ////////////////////////////////////////////////////////////////////////
    // The following is based on "Scientific Unit Conversion" 2nd Ed. by
    // Francois Cardarelli, Springer.
    // TODO: Consider splitting these out into a ConversionFactors interface,
    //  implemented by this class.
    // NOTE: The drawback to this approach is making them private, inaccessible
    //  for tight-loop usage or for usage as scale factors in an affine
    //  transform matrix. This needs further investigation.
    public static final double YARDS_TO_FEET_RATIO = 3.0d;
    public static final double FEET_TO_YARDS_RATIO = 1.0d / YARDS_TO_FEET_RATIO;
    public static final double FEET_TO_INCHES_RATIO = 12.0d;
    public static final double INCHES_TO_FEET_RATIO = 1.0d
                                                      / FEET_TO_INCHES_RATIO;
    public static final double YARDS_TO_INCHES_RATIO = YARDS_TO_FEET_RATIO
                                                       * FEET_TO_INCHES_RATIO;
    public static final double INCHES_TO_YARDS_RATIO = 1.0d
                                                       / YARDS_TO_INCHES_RATIO;
    public static final double METERS_TO_INCHES_RATIO = 39.370_078_74d;
    public static final double CENTIMETERS_TO_INCHES_RATIO = 0.01d
                                                             * METERS_TO_INCHES_RATIO;
    public static final double INCHES_TO_CENTIMETERS_RATIO = 1.0d
                                                             / CENTIMETERS_TO_INCHES_RATIO;
    public static final double MILLIMETERS_TO_INCHES_RATIO = 0.001d
                                                             * METERS_TO_INCHES_RATIO;
    public static final double INCHES_TO_MILLIMETERS_RATIO = 1.0d
                                                             / MILLIMETERS_TO_INCHES_RATIO;
    public static final double INCHES_TO_METERS_RATIO = 1.0d
                                                        / METERS_TO_INCHES_RATIO;
    public static final double FEET_TO_METERS_RATIO = FEET_TO_INCHES_RATIO
                                                      * INCHES_TO_METERS_RATIO;
    public static final double FEET_TO_CENTIMETERS_RATIO = 100.0d
                                                           * FEET_TO_METERS_RATIO;
    public static final double FEET_TO_MILLIMETERS_RATIO = 1_000.0d
                                                           * FEET_TO_METERS_RATIO;
    public static final double METERS_TO_FEET_RATIO = 1.0d
                                                      / FEET_TO_METERS_RATIO;
    public static final double CENTIMETERS_TO_FEET_RATIO = 0.01d
                                                           * METERS_TO_FEET_RATIO;
    public static final double MILLIMETERS_TO_FEET_RATIO = 0.001d
                                                           * METERS_TO_FEET_RATIO;
    public static final double YARDS_TO_METERS_RATIO = YARDS_TO_INCHES_RATIO
                                                       * INCHES_TO_METERS_RATIO;
    public static final double YARDS_TO_CENTIMETERS_RATIO = 100.0d
                                                            * YARDS_TO_METERS_RATIO;
    public static final double YARDS_TO_MILLIMETERS_RATIO = 1_000.0d
                                                            * YARDS_TO_METERS_RATIO;
    public static final double METERS_TO_YARDS_RATIO = 1.0d
                                                       / YARDS_TO_METERS_RATIO;
    public static final double CENTIMETERS_TO_YARDS_RATIO = 0.01d
                                                            * METERS_TO_YARDS_RATIO;
    public static final double MILLIMETERS_TO_YARDS_RATIO = 0.001d
                                                            * METERS_TO_YARDS_RATIO;

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private DistanceConversion() {
    }

    public static double convertDistance( final double distance,
                                          final DistanceUnit distanceUnitOld,
                                          final DistanceUnit distanceUnitNew ) {
        // If the units didn't change, preserve accuracy by avoiding redundant
        // conversion.
        if ( distanceUnitNew == distanceUnitOld ) {
            return distance;
        }

        double distanceConverted = distance;
        switch ( distanceUnitOld ) {
            case METERS:
                switch ( distanceUnitNew ) {
                    case METERS:
                        break;
                    case CENTIMETERS:
                        distanceConverted = distance * 100.0d;
                        break;
                    case MILLIMETERS:
                        distanceConverted = distance * 1_000.0d;
                        break;
                    case YARDS:
                        distanceConverted = metersToYards( distance );
                        break;
                    case FEET:
                        distanceConverted = metersToFeet( distance );
                        break;
                    case INCHES:
                        distanceConverted = metersToInches( distance );
                        break;
                    case UNITLESS:
                        break;
                    default:
                        break;
                }
                break;
            case CENTIMETERS:
                switch ( distanceUnitNew ) {
                    case METERS:
                        distanceConverted = distance * 0.01d;
                        break;
                    case CENTIMETERS:
                        break;
                    case MILLIMETERS:
                        distanceConverted = distance * 10.0d;
                        break;
                    case YARDS:
                        distanceConverted = centimetersToYards( distance );
                        break;
                    case FEET:
                        distanceConverted = centimetersToFeet( distance );
                        break;
                    case INCHES:
                        distanceConverted = centimetersToInches( distance );
                        break;
                    case UNITLESS:
                        break;
                    default:
                        break;
                }
                break;
            case MILLIMETERS:
                switch ( distanceUnitNew ) {
                    case METERS:
                        distanceConverted = distance * 0.001d;
                        break;
                    case CENTIMETERS:
                        distanceConverted = distance * 0.1d;
                        break;
                    case MILLIMETERS:
                        break;
                    case YARDS:
                        distanceConverted = millimetersToYards( distance );
                        break;
                    case FEET:
                        distanceConverted = millimetersToFeet( distance );
                        break;
                    case INCHES:
                        distanceConverted = millimetersToInches( distance );
                        break;
                    case UNITLESS:
                        break;
                    default:
                        break;
                }
                break;
            case YARDS:
                switch ( distanceUnitNew ) {
                    case METERS:
                        distanceConverted = yardsToMeters( distance );
                        break;
                    case CENTIMETERS:
                        distanceConverted = yardsToCentimeters( distance );
                        break;
                    case MILLIMETERS:
                        distanceConverted = yardsToMillimeters( distance );
                        break;
                    case YARDS:
                        break;
                    case FEET:
                        distanceConverted = yardsToFeet( distance );
                        break;
                    case INCHES:
                        distanceConverted = yardsToInches( distance );
                        break;
                    case UNITLESS:
                        break;
                    default:
                        break;
                }
                break;
            case FEET:
                switch ( distanceUnitNew ) {
                    case METERS:
                        distanceConverted = feetToMeters( distance );
                        break;
                    case CENTIMETERS:
                        distanceConverted = feetToCentimeters( distance );
                        break;
                    case MILLIMETERS:
                        distanceConverted = feetToMillimeters( distance );
                        break;
                    case YARDS:
                        distanceConverted = feetToYards( distance );
                        break;
                    case FEET:
                        break;
                    case INCHES:
                        distanceConverted = feetToInches( distance );
                        break;
                    case UNITLESS:
                        break;
                    default:
                        break;
                }
                break;
            case INCHES:
                switch ( distanceUnitNew ) {
                    case METERS:
                        distanceConverted = inchesToMeters( distance );
                        break;
                    case CENTIMETERS:
                        distanceConverted = inchesToCentimeters( distance );
                        break;
                    case MILLIMETERS:
                        distanceConverted = inchesToMillimeters( distance );
                        break;
                    case YARDS:
                        distanceConverted = inchesToYards( distance );
                        break;
                    case FEET:
                        distanceConverted = inchesToFeet( distance );
                        break;
                    case INCHES:
                        break;
                    case UNITLESS:
                        break;
                    default:
                        break;
                }
                break;
            case UNITLESS:
                break;
            default:
                break;
        }

        return distanceConverted;
    }

    public static double centimetersToFeet( final double lengthCentimeters ) {
        return lengthCentimeters * CENTIMETERS_TO_FEET_RATIO;
    }

    public static double centimetersToInches( final double lengthCentimeters ) {
        return lengthCentimeters * CENTIMETERS_TO_INCHES_RATIO;
    }

    public static double centimetersToYards( final double lengthCentimeters ) {
        return lengthCentimeters * CENTIMETERS_TO_YARDS_RATIO;
    }

    public static double feetToCentimeters( final double lengthFeet ) {
        return lengthFeet * FEET_TO_CENTIMETERS_RATIO;
    }

    public static double feetToInches( final double lengthFeet ) {
        return lengthFeet * FEET_TO_INCHES_RATIO;
    }

    public static double feetToMeters( final double lengthFeet ) {
        return lengthFeet * FEET_TO_METERS_RATIO;
    }

    public static double feetToMillimeters( final double lengthFeet ) {
        return lengthFeet * FEET_TO_MILLIMETERS_RATIO;
    }

    public static double feetToYards( final double lengthFeet ) {
        return lengthFeet * FEET_TO_YARDS_RATIO;
    }

    public static double inchesToCentimeters( final double lengthInches ) {
        return lengthInches * INCHES_TO_CENTIMETERS_RATIO;
    }

    public static double inchesToFeet( final double lengthInches ) {
        return lengthInches * INCHES_TO_FEET_RATIO;
    }

    public static double inchesToMeters( final double lengthInches ) {
        return lengthInches * INCHES_TO_METERS_RATIO;
    }

    public static double inchesToMillimeters( final double lengthInches ) {
        return lengthInches * INCHES_TO_MILLIMETERS_RATIO;
    }

    public static double inchesToYards( final double lengthInches ) {
        return lengthInches * INCHES_TO_YARDS_RATIO;
    }

    public static double metersToFeet( final double lengthMeters ) {
        return lengthMeters * METERS_TO_FEET_RATIO;
    }

    public static double metersToInches( final double lengthMeters ) {
        return lengthMeters * METERS_TO_INCHES_RATIO;
    }

    public static double metersToYards( final double lengthMeters ) {
        return lengthMeters * METERS_TO_YARDS_RATIO;
    }

    public static double millimetersToFeet( final double lengthMillimeters ) {
        return lengthMillimeters * MILLIMETERS_TO_FEET_RATIO;
    }

    public static double millimetersToInches( final double lengthMillimeters ) {
        return lengthMillimeters * MILLIMETERS_TO_INCHES_RATIO;
    }

    public static double millimetersToYards( final double lengthMillimeters ) {
        return lengthMillimeters * MILLIMETERS_TO_YARDS_RATIO;
    }

    public static double yardsToCentimeters( final double lengthYards ) {
        return lengthYards * YARDS_TO_CENTIMETERS_RATIO;
    }

    public static double yardsToFeet( final double lengthYards ) {
        return lengthYards * YARDS_TO_FEET_RATIO;
    }

    public static double yardsToInches( final double lengthYards ) {
        return lengthYards * YARDS_TO_INCHES_RATIO;
    }

    public static double yardsToMeters( final double lengthYards ) {
        return lengthYards * YARDS_TO_METERS_RATIO;
    }

    public static double yardsToMillimeters( final double lengthYards ) {
        return lengthYards * YARDS_TO_MILLIMETERS_RATIO;
    }
}
