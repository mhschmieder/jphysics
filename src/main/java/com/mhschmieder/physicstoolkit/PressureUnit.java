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
 * An enumeration of the most relevant pressure units for air attenuation.
 * <p>
 * NOTE: The labels account for the standard of leaving a space between the
 *  numeric value and its associated unit. The utility for making a Combo Box
 *  from an enum trims the space; other contexts need the space for separation.
 */
public enum PressureUnit implements Labeled< PressureUnit >, 
        Abbreviated< PressureUnit > {
    KILOPASCALS( "kilopascals", " kPa" ), 
    PASCALS( "pascals", " Pa" ), 
    MILLIBARS( "millibars", " mb" ), 
    ATMOSPHERES( "atmospheres", " atm" );
    
    private String label;
    private String abbreviation;
    
    PressureUnit( final String pLabel,
                  final String pAbbreviation ) {
        label = pLabel;
        abbreviation = pAbbreviation;
    }

    @Override
    public final String label() {
        return label;
    }

    @Override
    public PressureUnit valueOfLabel( final String text ) {
        return ( PressureUnit ) EnumUtilities.getLabeledEnumFromLabel(
                text, values() );
    }

    @Override
    public final String abbreviation() {
        return abbreviation;
    }

    @Override
    public PressureUnit valueOfAbbreviation( final String abbreviatedText ) {
        return ( PressureUnit ) EnumUtilities
                .getAbbreviatedEnumFromAbbreviation( abbreviatedText, values() );
    }


    public static PressureUnit defaultValue() {
        return PASCALS;
    }

    public static PressureUnit defaultValueForAir() {
        return KILOPASCALS;
    }

    @Override
    public String toString() {
        // NOTE: This override takes care of displaying the current choice in
        //  its custom label form when a Combo Box is hosted by a Table Cell.
        return label();
    }
}
