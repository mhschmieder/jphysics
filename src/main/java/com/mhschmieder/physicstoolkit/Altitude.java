/**
 * MIT License
 *
 * Copyright (c) 2020, 2025 Mark Schmieder
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
 * This file is part of the PhysicsToolkit Library
 *
 * You should have received a copy of the MIT License along with the
 * PhysicsToolkit Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/physicstoolkit
 */
package com.mhschmieder.physicstoolkit;

import java.util.Locale;

import org.apache.commons.math3.util.FastMath;

public enum Altitude {
    LOW, MEDIUM, HIGH;

    public static Altitude defaultValue() {
        return LOW;
    }

    public static Altitude fromCanonicalString( final String altitudeCanonicalString ) {
        return ( altitudeCanonicalString != null )
            ? valueOf( altitudeCanonicalString.toUpperCase( Locale.ENGLISH ) )
            : defaultValue();
    }

    public final String toCanonicalString() {
        return toString().toLowerCase( Locale.ENGLISH );
    }

    public final String toPresentationString( final DistanceUnit distanceUnit ) {
        final String distanceUnitString = distanceUnit.label();

        final int lowAltitude = ( int ) FastMath
                .round( UnitConversion.convertDistance( PhysicsConstants.ALTITUDE_LOW_METERS,
                                                        DistanceUnit.METERS,
                                                        distanceUnit ) );
        final int highAltitude = ( int ) FastMath
                .round( UnitConversion.convertDistance( PhysicsConstants.ALTITUDE_HIGH_METERS,
                                                        DistanceUnit.METERS,
                                                        distanceUnit ) );

        switch ( this ) {
        case LOW:
            return "Below " + Integer.toString( lowAltitude ) + " " //$NON-NLS-1$//$NON-NLS-2$
                    + distanceUnitString;
        case MEDIUM:
            return "Between " + Integer.toString( lowAltitude ) + " and " //$NON-NLS-1$ //$NON-NLS-2$
                    + Integer.toString( highAltitude ) + " " //$NON-NLS-1$
                    + distanceUnitString;
        case HIGH:
            return "Above " + Integer.toString( highAltitude ) + " " //$NON-NLS-1$//$NON-NLS-2$
                    + distanceUnitString;
        default:
            final String errMessage = "Unexpected Altitude " + this; //$NON-NLS-1$
            throw new IllegalArgumentException( errMessage );
        }
    }

}
