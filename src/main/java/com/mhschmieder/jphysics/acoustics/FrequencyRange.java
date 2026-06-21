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

// TODO: Switch octave range to either an enumeration with supporting transforms
// to presentation strings and lookup indices or to a direct lookup index.
public final class FrequencyRange {

    public static final String RELATIVE_BANDWIDTH_DEFAULT = RelativeBandwidth
            .defaultValue().label();
    public static final String OCTAVE_RANGE_WIDE_DEFAULT = "20 Hz To 20 kHz";              //$NON-NLS-1$
    public static final String OCTAVE_RANGE_NARROW_DEFAULT = "80 Hz To 160 Hz";              //$NON-NLS-1$
    public static final String CENTER_FREQUENCY_DISPLAY_DEFAULT = "4 kHz";                        //$NON-NLS-1$

    // Determine the nominal default Center Frequency based on Octave Range.

    public static double getNominalCenterFrequencyDefaultForOctaveRange(
            final String sOctaveRange,
            final boolean narrowBand ) {
        double centerFrequencyDefault = 4000.0d;
        
        switch ( sOctaveRange ) {
        case FrequencyRange.OCTAVE_RANGE_WIDE_DEFAULT:
            centerFrequencyDefault = 4000.0d;
            break;
        case "10 Hz to 20 Hz":
            centerFrequencyDefault = narrowBand ? 15.6d : 16.0d;
            break;
        case "20 Hz to 40 Hz":
            centerFrequencyDefault = narrowBand ? 31.2d : 31.5d;
            break;
        case "40 Hz to 80 Hz":
            centerFrequencyDefault = narrowBand ? 62.5d : 63.0d;
            break;
        case "80 Hz to 160 Hz":
            centerFrequencyDefault = 125.0d;
            break;
        case "160 Hz to 315 Hz":
            centerFrequencyDefault = 250.0d;
            break;
        case "315 Hz to 630 Hz":
            centerFrequencyDefault = 500.0d;
            break;
        case "630 Hz to 1.25 kHz":
            centerFrequencyDefault = 1000.0d;
            break;
        case "1.25 kHz to 2.5 kHz":
            centerFrequencyDefault = 2000.0d;
            break;
        case "2.5 kHz to 5 kHz":
            centerFrequencyDefault = 4000.0d;
            break;
        case "5 kHz to 10 kHz":
            centerFrequencyDefault = 8000.0d;
            break;
        case "10 kHz to 20 kHz":
            centerFrequencyDefault = 16000.0d;
            break;
        default:
            break;
        }
        
        return centerFrequencyDefault;
    }

    // Determine the nominal default Octave Range based on Center Frequency.

    public static String getNominalOctaveRangeDefaultForCenterFrequency(
            final double centerFrequency ) {
        // NOTE: We account for current offsets of ranges.
        if ( centerFrequency < 19d ) {
            return "10 Hz to 20 Hz";
        }
        else if ( centerFrequency < 39d ) {
            return "20 Hz to 40 Hz";
        }
        else if ( centerFrequency < 78d ) {
            return "40 Hz to 80 Hz";
        }
        else if ( centerFrequency < 156d ) {
            return "80 Hz to 160 Hz";
        }
        else if ( centerFrequency < 312d ) {
            return "160 Hz to 315 Hz";
        }
        else if ( centerFrequency < 624d ) {
            return "315 Hz to 630 Hz";
        }
        else if ( centerFrequency < 1248d ) {
            return "630 Hz to 1.25 kHz";
        }
        else if ( centerFrequency < 2496d ) {
            return "1.25 kHz to 2.5 kHz";
        }
        else if ( centerFrequency < 4992d ) {
            return "2.5 kHz to 5 kHz";
        }
        else if ( centerFrequency < 9986d ) {
            return "5 kHz to 10 kHz";
        }
        else {
            return "10 kHz to 20 kHz";
        }
    }

    // Determine if the Center Frequency is within the given Octave Range.
    @SuppressWarnings("nls")
    public static boolean isCenterFrequencyInOctaveRange(
            final String sOctaveRange,
            final double centerFrequency ) {
        // NOTE: We have to special-case for the first and last Narrow Band
        // Octave Ranges, as they extend beyond the single Wide Band Octave
        // Range and thus should cue a "closest match" vs. a default.
        boolean frequencyInRange = false;
        
        switch ( sOctaveRange ) {
        case FrequencyRange.OCTAVE_RANGE_WIDE_DEFAULT:
            frequencyInRange = true;
            break;
        case "10 Hz to 20 Hz":
            frequencyInRange = centerFrequency < 19.0d;
            break;
        case "20 Hz to 40 Hz":
            frequencyInRange = ( centerFrequency >= 19.0d )
                    && ( centerFrequency < 39.0d );
            break;
        case "40 Hz to 80 Hz":
            frequencyInRange = ( centerFrequency >= 39.0d )
                    && ( centerFrequency < 78.0d );
            break;
        case "80 Hz to 160 Hz":
            frequencyInRange = ( centerFrequency >= 78d )
                    && ( centerFrequency < 156d );
            break;
        case "160 Hz to 315 Hz":
            frequencyInRange = ( centerFrequency >= 156.0d )
                    && ( centerFrequency < 312.0d );
            break;
        case "315 Hz to 630 Hz":
            frequencyInRange = ( centerFrequency >= 312.0d )
                    && ( centerFrequency < 624.0d );
            break;
        case "630 Hz to 1.25 kHz":
            frequencyInRange = ( centerFrequency >= 624.0d )
                    && ( centerFrequency < 1248.0d );
            break;
        case "1.25 kHz to 2.5 kHz":
            frequencyInRange = ( centerFrequency >= 1248.0d )
                    && ( centerFrequency < 2496.0d );
            break;
        case "2.5 kHz to 5 kHz":
            frequencyInRange = ( centerFrequency >= 2496.0d )
                    && ( centerFrequency < 4992.0d );
            break;
        case "5 kHz to 10 kHz":
            frequencyInRange = ( centerFrequency >= 4992.0d )
                    && ( centerFrequency < 9986.0d );
            break;
        case "10 kHz to 20 kHz":
            frequencyInRange = centerFrequency >= 9986.0d;
            break;
        default:
            break;
        }
        
        return frequencyInRange;
    }

    private RelativeBandwidth relativeBandwidth;
    private String            octaveRange;
    private double            centerFrequency;

    // This is the default constructor; it sets all instance variables to
    // default values.
    public FrequencyRange() {
        this(
                RelativeBandwidth.defaultValue(),
                OCTAVE_RANGE_WIDE_DEFAULT,
                4000.0d );
    }

    // This is the copy constructor, and is offered in place of clone() to
    // guarantee that the source object is never modified by the new target
    // object created here.
    public FrequencyRange( final FrequencyRange frequencyRange ) {
        this( frequencyRange.getRelativeBandwidth(),
              frequencyRange.getOctaveRange(),
              frequencyRange.getCenterFrequency() );
    }

    // This is the preferred constructor, when all initialization values are
    // known.
    public FrequencyRange( final RelativeBandwidth pRelativeBandwidth,
                           final String pOctaveRange,
                           final double pCenterFrequency ) {
        relativeBandwidth = pRelativeBandwidth;
        octaveRange = pOctaveRange;
        centerFrequency = pCenterFrequency;
    }

    // NOTE: Cloning is disabled as it is dangerous; use the copy constructor
    // instead.
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    // Default pseudo-constructor.
    void reset() {
        setRelativeBandwidth( RelativeBandwidth.defaultValue() );

        final String octaveRangeDefault = OCTAVE_RANGE_WIDE_DEFAULT;
        setOctaveRange( octaveRangeDefault );

        final double centerFrequencyDefault
                = getNominalCenterFrequencyDefaultForOctaveRange(
                        octaveRangeDefault, false );
        setCenterFrequency( centerFrequencyDefault );
    }

    // Fully qualified pseudo-constructor.
    protected void setFrequencyRange( final RelativeBandwidth pRelativeBandwidth,
                                      final String pOctaveRange,
                                      final double pCenterFrequency ) {
        setRelativeBandwidth( pRelativeBandwidth );
        setOctaveRange( pOctaveRange );
        setCenterFrequency( pCenterFrequency );
    }

    // Pseudo-copy constructor
    public void setFrequencyRange( final FrequencyRange frequencyRange ) {
        setFrequencyRange( frequencyRange.getRelativeBandwidth(),
                           frequencyRange.getOctaveRange(),
                           frequencyRange.getCenterFrequency() );
    }

    public void setRelativeBandwidth(
            final RelativeBandwidth pRelativeBandwidth ) {
        relativeBandwidth = ( pRelativeBandwidth != null )
            ? pRelativeBandwidth
            : RelativeBandwidth.defaultValue();
    }

    public RelativeBandwidth getRelativeBandwidth() {
        return relativeBandwidth;
    }

    public void setOctaveRange( final String pOctaveRange ) {
        octaveRange = ( pOctaveRange != null )
                ? pOctaveRange
                : OCTAVE_RANGE_WIDE_DEFAULT;
    }

    public String getOctaveRange() {
        return octaveRange;
    }

    public void setCenterFrequency( final double pCenterFrequency ) {
        centerFrequency = pCenterFrequency;
    }

    public double getCenterFrequency() {
        return centerFrequency;
    }
}
