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
import com.mhschmieder.commonstoolkit.lang.StringConstants;

/**
 * An enumeration of the most relevant temperature units for most contexts.
 * <p>
 * NOTE: Temperature Units are all capitalized, unlike most other units, as
 *  they are named after people.
 * <p>
 * NOTE: The labels in this context are ready for use in a Combo Box; whereas
 *  the abbreviations are more for tagging units to displayed or editable 
 *  values, and follow international conventions unique to each choice.
 */
public enum TemperatureUnit implements Labeled< TemperatureUnit >, 
        Abbreviated< TemperatureUnit > {
    KELVIN( "Kelvin", StringConstants.DEGREES_KELVIN ), 
    CELSIUS( "Celsius", StringConstants.DEGREES_CELSIUS ), 
    FAHRENHEIT( "Fahrenheit",StringConstants.DEGREES_FAHRENHEIT );
    
    private String label;
    private String abbreviation;
    
    TemperatureUnit( final String pLabel,
                     final String pAbbreviation ) {
        label = pLabel;
        abbreviation = pAbbreviation;
    }

    @Override
    public final String label() {
        return label;
    }

    @Override
    public TemperatureUnit valueOfLabel( final String text ) {
        return ( TemperatureUnit ) EnumUtilities.getLabeledEnumFromLabel(
                text, values() );
    }

    @Override
    public final String abbreviation() {
        return abbreviation;
    }

    @Override
    public TemperatureUnit valueOfAbbreviation( final String abbreviatedText ) {
        return ( TemperatureUnit ) EnumUtilities
                .getAbbreviatedEnumFromAbbreviation( abbreviatedText, values() );
    }

    public static TemperatureUnit defaultValue() {
        return KELVIN;
    }

    public static final TemperatureUnit defaultValueForAir() {
        return CELSIUS;
    }

    @Override
    public String toString() {
        // NOTE: This override takes care of displaying the current choice in
        //  its custom label form when a Combo Box is hosted by a Table Cell.
        return label();
    }

    public final String toCanonicalString() {
        String canonicalString = toString();

        // NOTE: Temperature Units are all capitalized, unlike most other
        //  units, as they are named after people.
        canonicalString = canonicalString.substring( 0, 1 )
                .concat( canonicalString.substring( 1 ).toLowerCase( Locale.ENGLISH ) );

        return canonicalString;
    }
}
