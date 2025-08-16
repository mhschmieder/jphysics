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

import com.mhschmieder.commonstoolkit.lang.Abbreviated;
import com.mhschmieder.commonstoolkit.lang.EnumUtilities;
import com.mhschmieder.commonstoolkit.lang.Labeled;

/**
 * The <code>PivotFrom</code> enum is an enumeration for "pivot from" values for
 * rigging or other contexts.
 *
 * NOTE: Other than for string-based values, this is now redundant with JavaFX.
 */
public enum PivotFrom implements Labeled< PivotFrom >, Abbreviated< PivotFrom > {
    REAR( "Rear", "r" ), 
    FRONT( "Front", "f" );
    
    private String label;
    private String abbreviation;
    
    PivotFrom( final String pLabel,
               final String pAbbreviation ) {
        label = pLabel;
        abbreviation = pAbbreviation;
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public PivotFrom valueOfLabel( final String text ) {
        return ( PivotFrom ) EnumUtilities.getLabeledEnumFromLabel(
                text, values() );
    }

    @Override
    public String abbreviation() {
        return abbreviation;
    }

    @Override
    public PivotFrom valueOfAbbreviation( final String abbreviatedText ) {
        return ( PivotFrom ) EnumUtilities.getAbbreviatedEnumFromAbbreviation(
                abbreviatedText, values() );
    }

    public static PivotFrom defaultValue() {
        return FRONT;
    }

    @Override
    public String toString() {
        // NOTE: This override takes care of displaying the current choice in
        //  its custom label form when a Combo Box is hosted by a Table Cell.
        return label();
    }

    public final String toCanonicalString() {
        return toString().toLowerCase( Locale.ENGLISH );
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
}
