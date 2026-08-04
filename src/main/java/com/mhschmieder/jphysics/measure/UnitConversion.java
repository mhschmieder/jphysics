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

import com.mhschmieder.jphysics.PhysicsConstants;
import org.apache.commons.math3.util.FastMath;

/**
 * The <code>UnitConversion</code> class is a container for various unit
 * conversions between common units of measurement.
 */
public final class UnitConversion {

    //
    // ////////////////////////////////////////////////////////////////////////
    // The following is based on "Scientific Unit Conversion" 2nd Ed. by
    // Francois Cardarelli, Springer.
    // TODO: Consider splitting these out into a ConversionFactors interface,
    // implemented by this class.
    // NOTE: The drawback to this approach is making them private, inaccessible
    // for tight-loop usage or for usage as scale factors in an affine transform
    // matrix. This needs further investigation.
    public static final double YARDS_TO_FEET_RATIO = 3.0d;
    public static final double FEET_TO_YARDS_RATIO = 1.0d / YARDS_TO_FEET_RATIO;
    public static final double FEET_TO_INCHES_RATIO = 12d;
    public static final double INCHES_TO_FEET_RATIO = 1.0d
                                                      / FEET_TO_INCHES_RATIO;
    public static final double YARDS_TO_INCHES_RATIO = YARDS_TO_FEET_RATIO
                                                       * FEET_TO_INCHES_RATIO;
    public static final double INCHES_TO_YARDS_RATIO = 1.0d
                                                       / YARDS_TO_INCHES_RATIO;
    public static final double METERS_TO_INCHES_RATIO = 39.37007874d;
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
    public static final double FEET_TO_CENTIMETERS_RATIO = 100d
                                                           * FEET_TO_METERS_RATIO;
    public static final double FEET_TO_MILLIMETERS_RATIO = 1000d
                                                           * FEET_TO_METERS_RATIO;
    public static final double METERS_TO_FEET_RATIO = 1.0d
                                                      / FEET_TO_METERS_RATIO;
    public static final double CENTIMETERS_TO_FEET_RATIO = 0.01d
                                                           * METERS_TO_FEET_RATIO;
    public static final double MILLIMETERS_TO_FEET_RATIO = 0.001d
                                                           * METERS_TO_FEET_RATIO;
    public static final double YARDS_TO_METERS_RATIO = YARDS_TO_INCHES_RATIO
                                                       * INCHES_TO_METERS_RATIO;
    public static final double YARDS_TO_CENTIMETERS_RATIO = 100d
                                                            * YARDS_TO_METERS_RATIO;
    public static final double YARDS_TO_MILLIMETERS_RATIO = 1000d
                                                            * YARDS_TO_METERS_RATIO;
    public static final double METERS_TO_YARDS_RATIO = 1.0d
                                                       / YARDS_TO_METERS_RATIO;
    public static final double CENTIMETERS_TO_YARDS_RATIO = 0.01d
                                                            * METERS_TO_YARDS_RATIO;
    public static final double MILLIMETERS_TO_YARDS_RATIO = 0.001d
                                                            * METERS_TO_YARDS_RATIO;
    public static final double POUNDS_TO_OUNCES_RATIO = 16d;
    public static final double OUNCES_TO_POUNDS_RATIO = 1.0d
                                                        / POUNDS_TO_OUNCES_RATIO;
    public static final double METRIC_TONS_TO_POUNDS_RATIO = 2204.6232402d;
    public static final double KILOGRAMS_TO_POUNDS_RATIO = 0.001d
                                                           * METRIC_TONS_TO_POUNDS_RATIO;
    public static final double GRAMS_TO_POUNDS_RATIO = 0.001d
                                                       * KILOGRAMS_TO_POUNDS_RATIO;
    public static final double POUNDS_TO_GRAMS_RATIO = 1.0d
                                                       / GRAMS_TO_POUNDS_RATIO;
    public static final double POUNDS_TO_KILOGRAMS_RATIO = 1.0d
                                                           / KILOGRAMS_TO_POUNDS_RATIO;
    public static final double POUNDS_TO_METRIC_TONS_RATIO = 1.0d
                                                             / METRIC_TONS_TO_POUNDS_RATIO;
    public static final double METRIC_TONS_TO_OUNCES_RATIO =
            METRIC_TONS_TO_POUNDS_RATIO * POUNDS_TO_OUNCES_RATIO;
    public static final double KILOGRAMS_TO_OUNCES_RATIO = 0.001d
                                                           * METRIC_TONS_TO_OUNCES_RATIO;
    public static final double GRAMS_TO_OUNCES_RATIO = 0.001d
                                                       * KILOGRAMS_TO_OUNCES_RATIO;
    public static final double OUNCES_TO_GRAMS_RATIO = 1.0d
                                                       / GRAMS_TO_OUNCES_RATIO;
    public static final double OUNCES_TO_KILOGRAMS_RATIO = 1.0d
                                                           / KILOGRAMS_TO_OUNCES_RATIO;
    public static final double OUNCES_TO_METRIC_TONS_RATIO = 1.0d
                                                             / METRIC_TONS_TO_OUNCES_RATIO;

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

    public static final double FAHRENHEIT_TO_CELSIUS_RATIO = 9.0d / 5.0d;
    public static final double CELSIUS_TO_FAHRENHEIT_RATIO = 1.0d
                                                             / FAHRENHEIT_TO_CELSIUS_RATIO;
    public static final double CELSIUS_TO_FAHRENHEIT_ADJUSTMENT = 32.0d;
    public static final double CELSIUS_TO_KELVIN_ADJUSTMENT = 273.15d;

    // Conversion ratio for pound-force to newton, as a unit of force.
    public static final double POUND_FORCE_TO_NEWTONS_RATIO = 4.448_221_615_260_5d;
    public static final double NEWTONS_TO_POUND_FORCE_RATIO = 1.0d
                                                              / POUND_FORCE_TO_NEWTONS_RATIO;

    // Conversion ratios between mass and weight (measured in kilograms).
    public static final double MASS_TO_WEIGHT_RATIO
            =
            PhysicsConstants.ACCELERATION_OF_GRAVITY_METERS_PER_SECOND_SQUARED;
    public static final double WEIGHT_TO_MASS_RATIO = 1.0d
                                                      / MASS_TO_WEIGHT_RATIO;

    // Meters per Second (m/s) is more precise converted to Knots than
    // vice versa, so we initially express that ratio and derive the other.
    public static final double METERS_PER_SECOND_TO_KNOTS = 1.943_844_492_440_6d;
    public static final double KNOTS_TO_METERS_PER_SECOND = 1.0d
                                                            / METERS_PER_SECOND_TO_KNOTS;

    /**
     * The default constructor is disabled, as this is a static utilities
     * class.
     */
    private UnitConversion() {
    }

    // TODO: Use switch statements instead.
    public static double convertAngle( final double angle,
                                       final AngleUnit angleUnitOld,
                                       final AngleUnit angleUnitNew ) {
        // If the units didn't change, preserve accuracy by avoiding redundant
        // conversion.
        if ( angleUnitNew == angleUnitOld ) {
            return angle;
        }

        double angleConverted = angle;
        if ( angleUnitOld == AngleUnit.DEGREES ) {
            if ( angleUnitNew == AngleUnit.RADIANS ) {
                angleConverted = FastMath.toRadians( angle );
            }
        }
        else if ( angleUnitOld == AngleUnit.RADIANS ) {
            if ( angleUnitNew == AngleUnit.DEGREES ) {
                angleConverted = FastMath.toDegrees( angle );
            }
        }

        return angleConverted;
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

    // TODO: Use switch statements instead.
    public static double convertTemperature( final double temperature,
                                             final TemperatureUnit temperatureUnitOld,
                                             final TemperatureUnit temperatureUnitNew ) {
        // If the units didn't change, preserve accuracy by avoiding redundant
        // conversion.
        if ( temperatureUnitNew == temperatureUnitOld ) {
            return temperature;
        }

        double temperatureConverted = temperature;
        if ( temperatureUnitOld == TemperatureUnit.CELSIUS ) {
            if ( temperatureUnitNew == TemperatureUnit.FAHRENHEIT ) {
                temperatureConverted = celsiusToFahrenheit( temperature );
            }
            else if ( temperatureUnitNew == TemperatureUnit.KELVIN ) {
                temperatureConverted = celsiusToKelvin( temperature );
            }
        }
        else if ( temperatureUnitOld == TemperatureUnit.FAHRENHEIT ) {
            if ( temperatureUnitNew == TemperatureUnit.CELSIUS ) {
                temperatureConverted = fahrenheitToCelsius( temperature );
            }
            else if ( temperatureUnitNew == TemperatureUnit.KELVIN ) {
                temperatureConverted = fahrenheitToKelvin( temperature );
            }
        }
        else if ( temperatureUnitOld == TemperatureUnit.KELVIN ) {
            if ( temperatureUnitNew == TemperatureUnit.CELSIUS ) {
                temperatureConverted = kelvinToCelsius( temperature );
            }
            else if ( temperatureUnitNew == TemperatureUnit.FAHRENHEIT ) {
                temperatureConverted = kelvinToFahrenheit( temperature );
            }
        }

        return temperatureConverted;
    }

    public static double celsiusToFahrenheit( final double temperatureCelsius ) {
        return ( FAHRENHEIT_TO_CELSIUS_RATIO * temperatureCelsius )
               + CELSIUS_TO_FAHRENHEIT_ADJUSTMENT;
    }

    public static double celsiusToKelvin( final double temperatureCelsius ) {
        return temperatureCelsius + CELSIUS_TO_KELVIN_ADJUSTMENT;
    }

    public static double fahrenheitToCelsius( final double temperatureFahrenheit ) {
        return CELSIUS_TO_FAHRENHEIT_RATIO * ( temperatureFahrenheit
                                               - CELSIUS_TO_FAHRENHEIT_ADJUSTMENT );
    }

    public static double fahrenheitToKelvin( final double temperatureFahrenheit ) {
        return celsiusToKelvin( fahrenheitToCelsius( temperatureFahrenheit ) );
    }

    public static double kelvinToCelsius( final double temperatureKelvin ) {
        return temperatureKelvin - CELSIUS_TO_KELVIN_ADJUSTMENT;
    }

    public static double kelvinToFahrenheit( final double temperatureKelvin ) {
        return celsiusToFahrenheit( kelvinToCelsius( temperatureKelvin ) );
    }

    // TODO: Use switch statements instead.
    public static double convertWeight( final double weight,
                                        final WeightUnit weightUnitOld,
                                        final WeightUnit weightUnitNew ) {
        // If the units didn't change, preserve accuracy by avoiding redundant
        // conversion.
        if ( weightUnitNew == weightUnitOld ) {
            return weight;
        }

        double weightConverted = weight;
        if ( weightUnitOld == WeightUnit.METRIC_TONS ) {
            if ( weightUnitNew == WeightUnit.KILOGRAMS ) {
                weightConverted = weight * 1_000.0d;
            }
            else if ( weightUnitNew == WeightUnit.GRAMS ) {
                weightConverted = weight * 1_000_000.0d;
            }
            else if ( weightUnitNew == WeightUnit.POUNDS ) {
                weightConverted = metricTonsToPounds( weight );
            }
            else if ( weightUnitNew == WeightUnit.OUNCES ) {
                weightConverted = metricTonsToOunces( weight );
            }
        }
        else if ( weightUnitOld == WeightUnit.KILOGRAMS ) {
            if ( weightUnitNew == WeightUnit.METRIC_TONS ) {
                weightConverted = weight * 0.001d;
            }
            else if ( weightUnitNew == WeightUnit.GRAMS ) {
                weightConverted = weight * 1_000.0d;
            }
            else if ( weightUnitNew == WeightUnit.POUNDS ) {
                weightConverted = kilogramsToPounds( weight );
            }
            else if ( weightUnitNew == WeightUnit.OUNCES ) {
                weightConverted = kilogramsToOunces( weight );
            }
        }
        else if ( weightUnitOld == WeightUnit.GRAMS ) {
            if ( weightUnitNew == WeightUnit.METRIC_TONS ) {
                weightConverted = weight * 0.000_001d;
            }
            else if ( weightUnitNew == WeightUnit.KILOGRAMS ) {
                weightConverted = weight * 0.001d;
            }
            else if ( weightUnitNew == WeightUnit.POUNDS ) {
                weightConverted = gramsToPounds( weight );
            }
            else if ( weightUnitNew == WeightUnit.OUNCES ) {
                weightConverted = gramsToOunces( weight );
            }
        }
        else if ( weightUnitOld == WeightUnit.POUNDS ) {
            if ( weightUnitNew == WeightUnit.METRIC_TONS ) {
                weightConverted = poundsToMetricTons( weight );
            }
            else if ( weightUnitNew == WeightUnit.KILOGRAMS ) {
                weightConverted = poundsToKilograms( weight );
            }
            else if ( weightUnitNew == WeightUnit.GRAMS ) {
                weightConverted = poundsToGrams( weight );
            }
            else if ( weightUnitNew == WeightUnit.OUNCES ) {
                weightConverted = poundsToOunces( weight );
            }
        }
        else if ( weightUnitOld == WeightUnit.OUNCES ) {
            if ( weightUnitNew == WeightUnit.METRIC_TONS ) {
                weightConverted = ouncesToMetricTons( weight );
            }
            else if ( weightUnitNew == WeightUnit.KILOGRAMS ) {
                weightConverted = ouncesToKilograms( weight );
            }
            else if ( weightUnitNew == WeightUnit.GRAMS ) {
                weightConverted = ouncesToGrams( weight );
            }
            else if ( weightUnitNew == WeightUnit.POUNDS ) {
                weightConverted = ouncesToPounds( weight );
            }
        }

        return weightConverted;
    }

    public static double gramsToOunces( final double weightGrams ) {
        return weightGrams * GRAMS_TO_OUNCES_RATIO;
    }

    public static double gramsToPounds( final double weightGrams ) {
        return weightGrams * GRAMS_TO_POUNDS_RATIO;
    }

    public static double kilogramsToOunces( final double weightKilograms ) {
        return weightKilograms * KILOGRAMS_TO_OUNCES_RATIO;
    }

    public static double kilogramsToPounds( final double weightKilograms ) {
        return weightKilograms * KILOGRAMS_TO_POUNDS_RATIO;
    }

    public static double metricTonsToOunces( final double weightMetricTons ) {
        return weightMetricTons * METRIC_TONS_TO_OUNCES_RATIO;
    }

    public static double metricTonsToPounds( final double weightMetricTons ) {
        return weightMetricTons * METRIC_TONS_TO_POUNDS_RATIO;
    }

    public static double ouncesToGrams( final double weightOunces ) {
        return weightOunces * OUNCES_TO_GRAMS_RATIO;
    }

    public static double ouncesToKilograms( final double weightOunces ) {
        return weightOunces * OUNCES_TO_KILOGRAMS_RATIO;
    }

    public static double ouncesToMetricTons( final double weightOunces ) {
        return weightOunces * OUNCES_TO_METRIC_TONS_RATIO;
    }

    public static double ouncesToPounds( final double weightOunces ) {
        return weightOunces * OUNCES_TO_POUNDS_RATIO;
    }

    public static double poundsToGrams( final double weightPounds ) {
        return weightPounds * POUNDS_TO_GRAMS_RATIO;
    }

    public static double poundsToKilograms( final double weightPounds ) {
        return weightPounds * POUNDS_TO_KILOGRAMS_RATIO;
    }

    public static double poundsToMetricTons( final double weightPounds ) {
        return weightPounds * POUNDS_TO_METRIC_TONS_RATIO;
    }

    public static double poundsToOunces( final double weightPounds ) {
        return weightPounds * POUNDS_TO_OUNCES_RATIO;
    }

    public static double kilopascalsToPascals( final double pressureKilopascals ) {
        return pressureKilopascals * KILOPASCALS_TO_PASCALS_RATIO;
    }

    public static double newtonsToPoundForce( final double newtons ) {
        return newtons * NEWTONS_TO_POUND_FORCE_RATIO;
    }

    public static double pascalsToKilopascals( final double pressurePa ) {
        return pressurePa * PASCALS_TO_KILOPASCALS_RATIO;
    }

    public static double poundForceToNewtons( final double poundForce ) {
        return poundForce * POUND_FORCE_TO_NEWTONS_RATIO;
    }
}
