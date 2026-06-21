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
import com.mhschmieder.jcommons.lang.Labeled;

/**
 * Relative Bandwidth is a coarser concept than "Q" and is more specific to
 * particular acoustic engineering contexts where one is often more focused on
 * the practicalities of which center frequencies can be easily measured for
 * their response. The official definition dictionary definition is as follows:
 *
 * For an electric filter, the ratio of the bandwidth being considered to a
 * specified reference bandwidth, such as the bandwidth between frequencies at
 * which there is an attenuation of 3 decibels.
 *
 * This specific enumeration is meant to represent typical acoustical
 * measurement and detection environments, most commonly with a focus on what
 * are useful frequencies for evaluation and analysis, such as for music but
 * also for audio signals related to marine mammals, environment, sonar, etc.
 *
 * TODO: Expand this to also cover Relative Bandwidths that are multiple
 *  octaves? No need to expand in the other direction, as 1/48 octave is in
 *  most cases a single frequency (depending on the octave range context).
 */
public enum RelativeBandwidth implements Labeled< RelativeBandwidth >,
        Abbreviated< RelativeBandwidth > {
    ONE_OCTAVE( "1 octave", "1" ),
    THIRD_OCTAVE( "1/3 octave", "1/3" ),
    SIXTH_OCTAVE( "1/6 octave", "1/6" ),
    TWELFTH_OCTAVE( "1/12 octave", "1/12" ),
    TWENTY_FOURTH_OCTAVE( "1/24 octave", "1/24" ),
    FORTY_EIGHTH_OCTAVE( "1/48 octave", "1/48" );

    private final String label;
    private final String abbreviation;

    RelativeBandwidth( final String pLabel,
                       final String pAbbreviation ) {
        label = pLabel;
        abbreviation = pAbbreviation;
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public RelativeBandwidth valueOfLabel( final String text ) {
        return ( RelativeBandwidth ) EnumUtilities.getLabeledEnumFromLabel(
                text, values() );
    }

    @Override
    public String abbreviation() {
        return abbreviation;
    }

    @Override
    public RelativeBandwidth valueOfAbbreviation(
            final String abbreviatedText ) {
        return ( RelativeBandwidth ) EnumUtilities
                .getAbbreviatedEnumFromAbbreviation(
                        abbreviatedText, values() );
    }

    @Override
    public String toString() {
        // NOTE: This override takes care of displaying the current choice in
        //  its custom label form when a Combo Box is hosted by a Table Cell. It
        //  also addresses an issue with the Jackson parser if in a JSON file.
        return label();
    }

    public static RelativeBandwidth defaultValue() {
        return THIRD_OCTAVE;
    }

    /**
     * Returns the octave divider corresponding to the Relative Bandwidth,
     * which is always an integer and can be used as the denominator in various
     * acoustical calculations.
     *
     * @param relativeBandwidth
     *            The Relative bandwidth to convert to an Octave Divider
     * @return The integer value corresponding to the division factor to apply
     *         relative to a full octave, for the provided Relative Bandwidth
     *         value
     */
    public static int toOctaveDivider(
            final RelativeBandwidth relativeBandwidth ) {
        int octaveDivider = 3;
        
        switch ( relativeBandwidth ) {
        case ONE_OCTAVE:
            octaveDivider = 1;
            break;
        case THIRD_OCTAVE:
            octaveDivider = 3;
            break;
        case SIXTH_OCTAVE:
            octaveDivider = 6;
            break;
        case TWELFTH_OCTAVE:
            octaveDivider = 12;
            break;
        case TWENTY_FOURTH_OCTAVE:
            octaveDivider = 24;
            break;
        case FORTY_EIGHTH_OCTAVE:
            octaveDivider = 48;
            break;
        default:
            break;
        }
        
        return octaveDivider;
    }

    /**
     * Returns the octave divider corresponding to the Relative Bandwidth,
     * which is always an integer and can be used as the denominator in various
     * acoustical calculations.
     *
     * @return The integer value corresponding to the division factor to apply
     *         relative to a full octave, for this Relative Bandwidth value
     */
    public final int toOctaveDivider() {
        return toOctaveDivider( this );
    }
}
