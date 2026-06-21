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
package com.mhschmieder.jphysics.acoustics;

import com.mhschmieder.jcommons.lang.Abbreviated;
import com.mhschmieder.jcommons.lang.EnumUtilities;
import com.mhschmieder.jmath.MathConstants;
import org.apache.commons.math3.util.FastMath;

/**
 * This is an enumeration of possible resolution values for an SPL Palette. It
 * is done as an enumeration as some values are absolute and others are relative
 * to the overall SPL Dynamic Range.
 *
 * Note that there are two categories of values, where the first two are
 * strictly based on the number of unique color hues shown, and are based on
 * built-in limitations of standard color processing for basic image file types.
 *
 * The second set of values are more in the acoustics domain and are commonly
 * used in visualization contexts to help users see where values pass a
 * threshold. This is especially useful for showing where sound levels double.
 */
public enum SplPaletteResolution implements Abbreviated< SplPaletteResolution > {
    // Although these enumeration entries seem overly verbose and unnecessary,
    // the java programming language does not allow variables to start with a
    // number vs. a letter.
    RES_256( "256" ),
    RES_64( "64" ),
    RES_1DB( "1 dB" ),
    RES_2DB( "2 dB" ),
    RES_3DB( "3 dB" );

    private String abbreviation;

    SplPaletteResolution( final String pAbbreviation ) {
        abbreviation = pAbbreviation;
    }

    @Override
    public final String abbreviation() {
        return abbreviation;
    }

    @Override
    public SplPaletteResolution valueOfAbbreviation(
            final String abbreviatedText ) {
        return ( SplPaletteResolution ) EnumUtilities
                .getAbbreviatedEnumFromAbbreviation(
                        abbreviatedText, values() );
    }

    public static final SplPaletteResolution defaultValue() {
        return RES_64;
    }

    public final int getNumberOfPaletteColors( final int splRangeDb ) {
        int numberOfPaletteColors = 64;
        
        switch ( this ) {
        case RES_256:
            numberOfPaletteColors = 256;
            break;
        case RES_64:
            numberOfPaletteColors = 64;
            break;
        case RES_1DB:
            numberOfPaletteColors = splRangeDb;
            break;
        case RES_2DB:
            numberOfPaletteColors = ( int ) FastMath.ceil(
                    MathConstants.ONE_HALF * splRangeDb );
            break;
        case RES_3DB:
            numberOfPaletteColors = ( int ) FastMath.ceil(
                    MathConstants.ONE_THIRD * splRangeDb );
            break;
        default:
            return 64;
        }
        
        return numberOfPaletteColors;
    }
}
