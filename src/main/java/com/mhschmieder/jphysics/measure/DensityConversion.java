/*
 * MIT License
 *
 * Copyright (c) 2026 Mark Schmieder. All rights reserved.
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

public final class DensityConversion {

    /**
     * The default constructor is disabled, as this is a static utilities
     * class.
     */
    private DensityConversion() {
    }

    public static final double
            GRAMS_PER_CENTIMETER_CUBED_TO_KILOGRAMS_PER_METER_CUBED_RATIO
            = 1_000.0;
    public static final double
            KILOGRAMS_PER_METER_CUBED_TO_GRAMS_PER_CENTIMETER_CUBED_RATIO = 1.0
                                                                            / GRAMS_PER_CENTIMETER_CUBED_TO_KILOGRAMS_PER_METER_CUBED_RATIO;

    private static final String[] ABBREVIATIONS_LIST = {
            DensityUnit.KILOGRAMS_PER_METER_CUBED.abbreviation(),
            DensityUnit.GRAMS_PER_CENTIMETER_CUBED.abbreviation()
    };

    private static final String[] NAMES_LIST = {
            DensityUnit.KILOGRAMS_PER_METER_CUBED.name(),
            DensityUnit.GRAMS_PER_CENTIMETER_CUBED.name()
    };

    public static String[] getAbbreviationsList() {
        return ABBREVIATIONS_LIST;
    }

    public static String[] getNamesList() {
        return NAMES_LIST;
    }

    public static double convertDensity( final double density,
                                         final DensityUnit oldDensity,
                                         final DensityUnit newDensity ) {
        // If the units didn't change, preserve accuracy by avoiding redundant
        // conversion.
        if ( newDensity == oldDensity ) {
            return density;
        }

        // Default to no conversion, though this is redundant with the above.
        double densityConverted = density;

        switch ( oldDensity ) {
            case KILOGRAMS_PER_METER_CUBED -> {
                switch ( newDensity ) {
                    case GRAMS_PER_CENTIMETER_CUBED -> densityConverted
                                                               =
                                                               kilogramsPerMeterCubedToGramsPerCentimeterCubed(
                            density );
                }
            }
            case GRAMS_PER_CENTIMETER_CUBED -> {
                switch ( newDensity ) {
                    case KILOGRAMS_PER_METER_CUBED -> densityConverted
                                                              =
                                                              gramsPerCentimeterCubedToKilogramsPerMeterCubed(
                            density );
                }
            }
        }

        return densityConverted;
    }

    public static double gramsPerCentimeterCubedToKilogramsPerMeterCubed( final double densityGramsPerCentimeterCubed ) {
        return densityGramsPerCentimeterCubed
               * GRAMS_PER_CENTIMETER_CUBED_TO_KILOGRAMS_PER_METER_CUBED_RATIO;
    }

    public static double kilogramsPerMeterCubedToGramsPerCentimeterCubed( final double densityKilogramsPerMeterCubed ) {
        return densityKilogramsPerMeterCubed
               * KILOGRAMS_PER_METER_CUBED_TO_GRAMS_PER_CENTIMETER_CUBED_RATIO;
    }
}
