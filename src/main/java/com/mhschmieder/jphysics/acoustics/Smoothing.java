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

import com.mhschmieder.jcommons.lang.EnumUtilities;
import com.mhschmieder.jcommons.lang.Labeled;

/**
 * Smoothing generally refers to operations over specific Frequency Band Q
 * Factors. This enumeration also provides convenience methods for conversion
 * to octave dividers, which are of course trivial to derive but having them
 * as API allows for clean "foreach" syntax when looping the enum values.
 * <p>
 * TODO: Fully implement the recommended coding pattern for working with the
 *  new Labeled generic interface and associated list preparation tools.
 */
public enum Smoothing implements Labeled< Smoothing > {
    NARROW_BAND( "No Smoothing" ), 
    SIXTH_OCTAVE_BAND( "1/6 Octave Smoothing" ), 
    THIRD_OCTAVE_BAND( "1/3 Octave Smoothing" );
    
    private final String label;
    
    Smoothing( final String pLabel ) {
        label = pLabel;
    }

    @Override 
    public String label() {
        return label;
    }

    @Override
    public Smoothing valueOfLabel( final String text ) {
        return ( Smoothing ) EnumUtilities.getLabeledEnumFromLabel( 
            text, values() );
    }

    @Override
    public String toString() {
        // NOTE: This override takes care of displaying the current choice in
        //  its custom label form when a Combo Box is hosted by a Table Cell. It
        //  also addresses an issue with the Jackson parser if in a JSON file.
        return label();
    }

    public static Smoothing defaultValue() {
        return NARROW_BAND;
    }

    public static Smoothing fromOctaveDivider(final int octaveDivider) {
        Smoothing smoothing = defaultValue();
        
        switch ( octaveDivider ) {
        case 0:
            smoothing = NARROW_BAND;
            break;
        case 6:
            smoothing = SIXTH_OCTAVE_BAND;
            break;
        case 3:
            smoothing = THIRD_OCTAVE_BAND;
            break;
        default:
            break; 
        }
        
        return smoothing;
    }

    public static int toOctaveDivider(final Smoothing smoothing) {
        int octaveDivider = -1;
        
        switch ( smoothing ) {
        case NARROW_BAND:
            octaveDivider = 0;
            break;
        case SIXTH_OCTAVE_BAND:
            octaveDivider = 6;
            break;
        case THIRD_OCTAVE_BAND:
            octaveDivider = 3;
            break;
        default:
            final String errMessage = "Unexpected " 
                    + smoothing.getClass().getSimpleName() + " "
                    + smoothing;
            throw new IllegalArgumentException( errMessage );
        }
        
        return octaveDivider;
    }

    public final int toOctaveDivider() {
        return toOctaveDivider( this );
    }
}
