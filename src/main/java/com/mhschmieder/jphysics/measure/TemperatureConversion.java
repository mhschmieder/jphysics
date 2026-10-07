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

public final class TemperatureConversion {

    // ////////////////////////////////////////////////////////////////////////
    // The following is based on "Scientific Unit Conversion" 2nd Ed. by
    // Francois Cardarelli, Springer.
    // TODO: Consider splitting these out into a ConversionFactors interface,
    //  implemented by this class.
    // NOTE: The drawback to this approach is making them private, inaccessible
    //  for tight-loop usage or for usage as scale factors in an affine
    //  transform matrix. This needs further investigation.
    public static final double FAHRENHEIT_TO_CELSIUS_RATIO = 9.0d / 5.0d;
    public static final double CELSIUS_TO_FAHRENHEIT_RATIO = 1.0d
                                                             / FAHRENHEIT_TO_CELSIUS_RATIO;
    public static final double CELSIUS_TO_FAHRENHEIT_ADJUSTMENT = 32.0d;
    public static final double CELSIUS_TO_KELVIN_ADJUSTMENT = 273.15d;

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private TemperatureConversion() {
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
}
