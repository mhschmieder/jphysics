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

/**
 * The {@code UnitConversion} class is a container for various unit conversions
 * between units of measurement that aren't yet enumerated by this library.
 */
public final class UnitConversion {

    // Conversion ratio for pound-force to newton, as a unit of force.
    public static final double POUND_FORCE_TO_NEWTONS_RATIO
            = 4.448_221_615_260_5d;
    public static final double NEWTONS_TO_POUND_FORCE_RATIO = 1.0d
                                                              / POUND_FORCE_TO_NEWTONS_RATIO;

    // Conversion ratios between mass and                                                                                                                                                                   weight (measured in kilograms).
    public static final double MASS_TO_WEIGHT_RATIO
            =
            PhysicsConstants.ACCELERATION_OF_GRAVITY_METERS_PER_SECOND_SQUARED;
    public static final double WEIGHT_TO_MASS_RATIO = 1.0d
                                                      / MASS_TO_WEIGHT_RATIO;

    // Meters per Second (m/s) is more precise converted to Knots than vice
    // versa, so we initially express that ratio and derive the other.
    public static final double METERS_PER_SECOND_TO_KNOTS
            = 1.943_844_492_440_6d;
    public static final double KNOTS_TO_METERS_PER_SECOND = 1.0d
                                                            / METERS_PER_SECOND_TO_KNOTS;

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private UnitConversion() {
    }

    public static double newtonsToPoundForce( final double newtons ) {
        return newtons * NEWTONS_TO_POUND_FORCE_RATIO;
    }

    public static double poundForceToNewtons( final double poundForce ) {
        return poundForce * POUND_FORCE_TO_NEWTONS_RATIO;
    }
}
