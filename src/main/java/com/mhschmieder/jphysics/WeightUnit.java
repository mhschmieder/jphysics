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

public enum WeightUnit implements Labeled< WeightUnit >, 
        Abbreviated< WeightUnit > {
    METRIC_TONS( "metric tons", " mt" ), 
    KILOGRAMS( "kilograms", " kg" ), 
    GRAMS( "grams", " g" ), 
    POUNDS( "pounds", " lbs" ), 
    OUNCES( "ounces", " oz" );
    
    private String label;
    private String abbreviation;
    
    WeightUnit( final String pLabel,
                final String pAbbreviation ) {
        label = pLabel;
        abbreviation = pAbbreviation;
    }

    @Override
    public final String label() {
        return label;
    }

    @Override
    public WeightUnit valueOfLabel( final String text ) {
        return ( WeightUnit ) EnumUtilities.getLabeledEnumFromLabel( 
                text, values() );
    }

    @Override
    public final String abbreviation() {
        return abbreviation;
    }

    @Override
    public WeightUnit valueOfAbbreviation( final String abbreviatedText ) {
        return ( WeightUnit ) EnumUtilities
                .getAbbreviatedEnumFromAbbreviation( abbreviatedText, values() );
    }

    public static WeightUnit defaultValue() {
        return KILOGRAMS;
    }

    @Override
    public String toString() {
        // NOTE: This override takes care of displaying the current choice in
        //  its custom label form when a Combo Box is hosted by a Table Cell.
        return label();
    }
}
