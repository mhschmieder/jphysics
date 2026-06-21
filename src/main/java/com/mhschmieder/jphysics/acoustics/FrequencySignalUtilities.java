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

import com.mhschmieder.jcommons.text.NumberFormatUtilities;
import com.mhschmieder.jmath.MathConstants;
import com.mhschmieder.jmath.MathUtilities;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.FastMath;

import java.text.NumberFormat;

/**
 * General utilities for working with frequency signals in the analog domain.
 * These are fairly common requests that pop up in many fields, so it seems
 * appropriate to provide them in a top-level common library for acoustics.
 */
public final class FrequencySignalUtilities {

    // The octave bandwidth to quality factor ratio is often 1.41 using the
    // common equation for N = (2/ln2)*sinh^-1(1/(2*Q)), where N is the number
    // of octaves, but is also a quality factor that is somewhat up to the
    // discretion of the filter designer. This constant is for the one octave
    // bandwidth case, as that allows us to compute Q for other octave bands
    // using ratios to one octave. For some reason, this got set to 1.43.
    // See http://www.sengpielaudio.com/calculator-bandwidth.htm for details.
    public static final double OCTAVE_BANDWIDTH_TO_QUALITY_FACTOR_RATIO = 1.43d;

    /**
     * The default constructor is disabled, as this is a static utilities class
     */
    private FrequencySignalUtilities() {}

    // Convert magnitude (the square root of the sum of the squares of the real
    // and imaginary parts of a complex value) from linear to decibels.
    public static double convertComplexValueToDecibels(
            final Complex complexValue ) {
        return convertMagnitudeToDecibels( complexValue.abs() );
    }

    // Convert magnitude (the square root of the sum of the squares of the real
    // and imaginary parts of a complex value) from linear to decibels.
    public static double convertMagnitudeToDecibels( final double magnitude ) {
        return 20.0d * FastMath.log10( magnitude );
    }

    // Convert the power ratio from linear to decibels.
    public static double convertPowerRatioToDecibels(
            final double powerRatio ) {
        return 10.0d * FastMath.log10( powerRatio );
    }

    // Get the power ratio (decibels) from the voltage ratio (linear).
    public static double getPowerRatioDb( final double voltageRatio ) {
        return 20.0d * FastMath.log10( voltageRatio );
    }

    // Get the voltage ratio (linear) from the power ratio (decibels).
    //
    // This version of the method is for all but peaking and shelving filters.
    //
    // https://webaudio.github.io/Audio-EQ-Cookbook/Audio-EQ-Cookbook.txt
    public static double getVoltageRatio( final double powerRatioDb ) {
        return FastMath.pow( 10.0d, powerRatioDb / 20.0d );
    }

    // Get the peaking voltage ratio (linear) from the power ratio (decibels).
    //
    // This version of the method is strictly for peaking and shelving filters.
    //
    // https://webaudio.github.io/Audio-EQ-Cookbook/Audio-EQ-Cookbook.txt
    public static double getPeakingVoltageRatio( final double powerRatioDb ) {
        return FastMath.pow( 10.0d, powerRatioDb / 40.0d );
    }

    // Convert the magnitude from decibels to linear.
    public static double convertMagnitudeFromDecibels(
            final double magnitude ) {
        return FastMath.pow( 10.0d, magnitude / 20.0d );
    }

    // Convert the power ratio from decibels to linear.
    public static double convertPowerRatioFromDecibels(
            final double powerRatioDb ) {
        return FastMath.pow( 10.0d, powerRatioDb / 10.0d );
    }

    // Normalize a frequency phase vector to [-180, +180] range.
    // TODO: Determine whether this is any different from unwrapPhase().
    public static void normalizePhase( final double[] frequencyPhaseData,
                                       final int numberOfBins ) {
        // NOTE: We stop one shy of the last index due to comparisons.
        final int binFirstIndex = 0;
        final int binIndexLast = numberOfBins - 1;
        for ( int binIndex = binFirstIndex; binIndex < binIndexLast; binIndex++ ) {
            final double phase = frequencyPhaseData[ binIndex ];
            double nextPhase = frequencyPhaseData[ binIndex + 1 ];
            double phaseDiff = phase - nextPhase;

            while ( phaseDiff > 180.0d ) {
                nextPhase += 360.0d;
                phaseDiff = phase - nextPhase;
            }

            phaseDiff = nextPhase - phase;

            while ( phaseDiff > 180.0d ) {
                nextPhase -= 360.0d;
                phaseDiff = nextPhase - phase;
            }

            frequencyPhaseData[ binIndex + 1 ] = nextPhase;
        }
    }

    // Unwrap a single frequency phase value to the [-180, +180] range.
    public static double unwrapPhase( final double frequencyPhase ) {
        return MathUtilities.normalizeAngleDegrees( frequencyPhase, 0.0d );
    }

    // Unwrap a frequency phase vector to the [-180 180] range.
    public static void unwrapPhase( final double[] frequencyPhaseData, final int numberOfBins ) {
        for ( int binIndex = 0; binIndex < numberOfBins; binIndex++ ) {
            final double frequencyPhase = frequencyPhaseData[ binIndex ];
            final double unwrappedFrequencyPhase = unwrapPhase( frequencyPhase );
            frequencyPhaseData[ binIndex ] = unwrappedFrequencyPhase;
        }
    }

    // Clean up a frequency phase vector to avoid flips between -180 and +180.
    public static void cleanupPhase( final double[] frequencyPhaseData,
                                     final int numberOfBins ) {
        // NOTE: We stop one shy of the last index due to comparisons.
        final int binFirstIndex = 0;
        final int binIndexLast = numberOfBins - 1;
        for ( int binIndex = binFirstIndex; binIndex < binIndexLast; binIndex++ ) {
            final double phase = frequencyPhaseData[ binIndex ];
            final double nextPhase = frequencyPhaseData[ binIndex + 1 ];

            // Avoid constant flipping between -180 and +180, as this can cause
            // anomalies in downstream charting clients that draw lines to
            // connect neighboring data points, which is perceived as wrapping.
            if ( ( ( nextPhase > 179.9999d ) && ( nextPhase < 180.0001d ) )
                    || ( ( nextPhase < -179.9999d ) && ( nextPhase > -180.0001d ) ) ) {
                frequencyPhaseData[ binIndex + 1 ] = ( phase <= 0.0d ) ? -180.0d : 180.0d;
            }
        }
    }

    // Clean up a frequency phase vector to set all +180 values to -180 for
    // polarity reversed, as that is the convention (though both are the same).
    public static void cleanupPolarity( final double[] frequencyPhaseData,
                                        final int numberOfBins ) {
        final int binFirstIndex = 0;
        final int binIndexLast = numberOfBins - 1;
        for ( int binIndex = binFirstIndex; binIndex <= binIndexLast; binIndex++ ) {
            final double phase = frequencyPhaseData[ binIndex ];
            if ( ( phase > 179.9999d ) && ( phase < 180.0001d ) ) {
                frequencyPhaseData[ binIndex ] = -180.0d;
            }
        }
    }

    // Function to expand a potentially metric abbreviated frequency to its
    // complete specification, accounting for locale formatting, and with or
    // without a space between the number and the unit.
    public static double expandMetricAbbreviatedFrequency(
            final String metricAbbreviatedFrequency,
            final NumberFormat numberParse ) {
        // First note whether the string representation even includes units.
        final boolean hasUnits = metricAbbreviatedFrequency.contains( "Hz" );

        // Identify whether the scale of the frequency is in Hertz or Kilohertz.
        final boolean isMetricAbbreviated = hasUnits
            ? metricAbbreviatedFrequency.endsWith( "kHz" )
            : false;

        // Get the index of the units string, not yet accounting for spaces.
        final int unitsIndex = isMetricAbbreviated
            ? metricAbbreviatedFrequency.lastIndexOf( 'k' )
            : metricAbbreviatedFrequency.lastIndexOf( 'H' );

        // Strip the numeric string of its units, and any space separators.
        final int startIndex = 0; // inclusive range bounds
        final int endIndex = unitsIndex + 1; // exclusive range bounds
        final String numericString = metricAbbreviatedFrequency.substring( startIndex, endIndex )
                .trim();

        // Use the locale formatting to determine the actual number.
        final double frequency = NumberFormatUtilities.parseDouble( numericString, numberParse );

        // Conditionally adjust the frequency to account for thousands.
        return isMetricAbbreviated ? frequency * 1000.0d : frequency;
    }

    // Gets the center frequency from the band number by implementing the
    // equation: fc = 1000 * 2^( (N-M)/O ), where fc is the center frequency, N
    // is the band number (M is the band number for 1000 Hz), and O is the
    // octave divider, but for simplicity's sake we refactor both M and O to be
    // in terms of third octave values.
    public static double getCenterFrequencyByBandNumber(
            final int bandNumber,
            final double octaveDivider ) {
        final double octaveDividerRatio = octaveDivider / 3.0d;
        final int bandNumberAt1kHz = ( int ) FastMath.round(
                octaveDividerRatio * 30.0d );
        final int numberOfFractionalOctaveBandsFrom1kHz
                = bandNumber - bandNumberAt1kHz;
        return 1000.0d * FastMath.pow(
                2.0d,
                numberOfFractionalOctaveBandsFrom1kHz / octaveDivider );
    }

    // TODO: Make an enumeration or indexed lookup of octave ranges, as they are
    //  standard and not up for interpretation or product-specific assignments.
    public static int getOctaveOffsetFrom10Hz( final String octaveRange ) {
        int octaveOffset = 0;
        
        switch ( octaveRange ) {
        case "10 Hz to 20 Hz":
            octaveOffset = 0;
            break;
        case "20 Hz to 40 Hz":
            octaveOffset = 1;
            break;
        case "40 Hz to 80 Hz":
            octaveOffset = 2;
            break;
        case "80 Hz to 160 Hz":
            octaveOffset = 3;
            break;
        case "160 Hz to 315 Hz":
            octaveOffset = 4;
            break;
        case "315 Hz to 630 Hz":
            octaveOffset = 5;
            break;
        case "630 Hz to 1.25 kHz":
            octaveOffset = 6;
            break;
        case "1.25 kHz to 2.5 kHz":
            octaveOffset = 7;
            break;
        case "2.5 kHz to 5 kHz":
            octaveOffset = 8;
            break;
        case "5 kHz to 10 kHz":
            octaveOffset = 9;
            break;
        case "10 kHz to 20 kHz":
            octaveOffset = 10;
            break;
        default:
            break;
        }
        
        return octaveOffset;
    }

    public static String getFormattedFrequency(
            final double frequency,
            final NumberFormat numberFormat ) {
        String formattedFrequency = "";

        if ( frequency < 1000.0d ) {
            // Use up to one digit of precision, to cover normal spacing of low
            // to mid-range frequencies.
            numberFormat.setMinimumFractionDigits( 0 );
            numberFormat.setMaximumFractionDigits( 1 );
            formattedFrequency = numberFormat.format( frequency ) + " Hz";
        }
        else {
            // Use up to four digits of precision, to cover the kHz scaling.
            numberFormat.setMinimumFractionDigits( 0 );
            numberFormat.setMaximumFractionDigits( 4 );
            formattedFrequency = numberFormat.format( 0.001d * frequency ) + " kHz";
        }

        return formattedFrequency;
    }

    // Convert a frequency (in Hertz) to the s-Domain (analog).
    // NOTE: Although this method is most often called in the midst of digital
    // filter calculations, it isn't digital in nature, so belongs here amongst
    // the other analog domain (aka s-domain, or frequency domain) methods.
    // NOTE: Due to the irrelevance of sigma variance in most contexts, this is
    //  equivalent to returning the pure sinusoidal filter slope as a function
    //  of frequency.
    public static Complex convertFrequencyToSDomain(
            final double frequencyHz ) {
        // Get the angular frequency in radians based on the frequency in Hertz.
        final double angularFrequencyRadians = FrequencySignalUtilities
                .getAngularFrequencyRadians( frequencyHz );

        // Calculate the sinusoidal filter slope as a function of frequency
        // "f": slope = -J*2*PI*f (where J = sqrt(-1)), and return as the omega
        // term in "s" for Laplace (blank the unused sigma term), where "s" =
        // sigma + j*omega.
        return new Complex( 0.0d, angularFrequencyRadians );
    }

    // Get the angular frequency in radians based on the frequency in Hertz.
    public static double getAngularFrequencyRadians(
            final double frequencyHz ) {
        return MathConstants.TWO_PI * frequencyHz;
    }

    // Convert a bandwidth to a quality factor ratio (aka Q).
    public static double convertBandwidthToQ( final double bandwidth ) {
        // First get the ratio to a bandwidth, as that is how we define our
        // reference Q Factor.
        final double referenceQFactor = 1.0d / bandwidth;

        // Combining the ratios should result in the correct Q Factor for any
        // arbitrary bandwidth.
        return referenceQFactor * OCTAVE_BANDWIDTH_TO_QUALITY_FACTOR_RATIO;
    }

    public static int[] getClampedFrequencyRangeIndices(
            final double[] bins,
            final boolean useLimitedFrequencyRange,
            final double lowestFrequencyToDisplay,
            final double highestFrequencyToDisplay ) {
        final int numberOfBins = bins.length;

        // Find the start and end indices for the valid sub-range of the bins.
        // NOTE: If not limiting the natural frequency range of the bins, we
        //  bypass the loops and use zero and array length minus one as the
        //  start and stop indices. Alternatively, this method could be skipped,
        // TODO: Recode this to use rounding instead?
        int startFreqIndex = 0;
        int stopFreqIndex = useLimitedFrequencyRange
                ? startFreqIndex
                : numberOfBins - 1;

        int freqIndex = startFreqIndex;
        if ( useLimitedFrequencyRange ) {
            while ( freqIndex < numberOfBins ) {
                if ( bins[ freqIndex ] >= lowestFrequencyToDisplay ) {
                    startFreqIndex = freqIndex;
                    break;
                }
                freqIndex++;
            }
            while ( freqIndex < numberOfBins ) {
                if ( bins[ freqIndex ] >= highestFrequencyToDisplay ) {
                    stopFreqIndex = freqIndex;
                    break;
                }
                freqIndex++;
            }
        }

        final int[] displayableFrequencyRangeIndices = new int[ 2 ];
        displayableFrequencyRangeIndices[ 0 ] = startFreqIndex;
        displayableFrequencyRangeIndices[ 1 ] = stopFreqIndex;
        return displayableFrequencyRangeIndices;
    }
}
