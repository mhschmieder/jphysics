/**
 * MIT License
 *
 * Copyright (c) 2020, 2023 Mark Schmieder
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

/**
 * The <code>PivotFrom</code> enum is an enumeration for "pivot from" values for
 * rigging or other contexts.
 *
 * NOTE: Other than for Presentation String, this is now redundant with JavaFX.
 */
public enum PivotFrom {
    REAR, FRONT;

    @SuppressWarnings("nls")
    public static PivotFrom abbreviatedValueOf( final String abbreviatedPivotFrom ) {
        return ( "r".equalsIgnoreCase( abbreviatedPivotFrom ) )
            ? REAR
            : ( "f".equalsIgnoreCase( abbreviatedPivotFrom ) ) ? FRONT : defaultValue();
    }

    @SuppressWarnings("nls")
    public static PivotFrom canonicalValueOf( final String canonicalPivotFrom ) {
        // Cover legacy cases, as we changed terminology at some point.
        return ( canonicalPivotFrom == null )
            ? defaultValue()
            : "back".equalsIgnoreCase( canonicalPivotFrom )
                ? REAR
                : "front".equalsIgnoreCase( canonicalPivotFrom )
                    ? FRONT
                    : valueOf( canonicalPivotFrom.toUpperCase( Locale.ENGLISH ) );
    }

    public static PivotFrom defaultValue() {
        return FRONT;
    }

    public final String toAbbreviatedString() {
        String abbreviatedString = null;
        
        switch ( this ) {
        case REAR:
            abbreviatedString = "r"; //$NON-NLS-1$
            break;
        case FRONT:
            abbreviatedString = "f"; //$NON-NLS-1$
            break;
        default:
            final String errMessage = "Unexpected " //$NON-NLS-1$
                    + this.getClass().getSimpleName() + " " + this; //$NON-NLS-1$
            throw new IllegalArgumentException( errMessage );
        }
        
        return abbreviatedString;
    }

    public final String toCanonicalString() {
        return toString().toLowerCase( Locale.ENGLISH );
    }

    public final String toPresentationString() {
        String presentationString = null;
        
        switch ( this ) {
        case REAR:
            presentationString = "Rear"; //$NON-NLS-1$
            break;
        case FRONT:
            presentationString = "Front"; //$NON-NLS-1$
            break;
        default:
            final String errMessage = "Unexpected " //$NON-NLS-1$
                    + this.getClass().getSimpleName() + " " + this; //$NON-NLS-1$
            throw new IllegalArgumentException( errMessage );
        }
        
        return presentationString;
    }

}// enum PivotFrom
