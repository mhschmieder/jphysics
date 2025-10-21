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
 * This file is part of the JPhysics Library
 *
 * You should have received a copy of the MIT License along with the
 * JPhysics Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/jphysics
 */
package com.mhschmieder.jphysics;

import com.mhschmieder.jcommons.lang.Abbreviated;
import com.mhschmieder.jcommons.lang.EnumUtilities;
import com.mhschmieder.jcommons.lang.Labeled;

/**
 * An enumeration of the most relevant length units for linear distance.
 * <p>
 * NOTE: The labels account for the standard of leaving a space between the
 *  numeric value and its associated unit. The utility for making a Combo Box
 *  from an enum trims the space; other contexts need the space for separation.
 * NOTE: The "Unitless" field is included because sometimes this is an interim
 *  value until units are known, or units weren't specified but we need to 
 *  track that as distinct from units not initialized within the client code.
 */
public enum DistanceUnit implements Labeled< DistanceUnit >, 
        Abbreviated< DistanceUnit > {
    UNITLESS( "unitless", "" ), 
    MILLIMETERS( "millimeters", " mm" ), 
    CENTIMETERS( "centimeters", " cm" ), 
    METERS( "meters", " m" ), 
    INCHES( "inches", " in" ),
    FEET( "feet", " ft" ), 
    YARDS( "yards", " yd" );
    
    private String label;
    private String abbreviation;
    
    DistanceUnit( final String pLabel,
                  final String pAbbreviation ) {
        label = pLabel;
        abbreviation = pAbbreviation;
    }

    public final String label() {
        return label;
    }

    public DistanceUnit valueOfLabel( final String text ) {
        return ( DistanceUnit ) EnumUtilities.getLabeledEnumFromLabel(
                text, values() );
    }

    public final String abbreviation() {
        return abbreviation;
    }

    public DistanceUnit valueOfAbbreviation( final String abbreviatedText ) {
        return ( DistanceUnit ) EnumUtilities
                .getAbbreviatedEnumFromAbbreviation( abbreviatedText, values() );
    }

    public static DistanceUnit defaultValue() {
        return METERS;
    }

    @Override
    public String toString() {
        // NOTE: This override takes care of displaying the current choice in
        //  its custom label form when a Combo Box is hosted by a Table Cell.
        return label();
    }
}
