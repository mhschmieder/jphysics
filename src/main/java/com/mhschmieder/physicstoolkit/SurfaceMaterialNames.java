/**
 * MIT License
 *
 * Copyright (c) 2020, 2023 Mark Schmieder
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

import java.util.ArrayList;
import java.util.List;

/**
 * Standard surface materials as specified in acoustic textbooks, designed to
 * cover the basic range of acoustic absorption properties and other attributes.
 * <p>
 * TODO: Make an enumeration of Surface Materials, with "toPresentationString()"
 *  as well as "toLegacyString()" (the latter coming from original textbook tags).
 */
public final class SurfaceMaterialNames {

    // //////////////////////////////////////////////////////////////////////////
    // Surface Materials
    public static final String ACOUSTIC_TILE_ON_RIGID_SURF_KF              =
                                                              "AcousticTileOnRigidSurfKF";                        //$NON-NLS-1$

    public static final String BRICK_WALL_PAINTED_LB                       = "BrickWallPaintedLB";                //$NON-NLS-1$
    public static final String BRICK_WALL_UNPAINTED_LB                     = "BrickWallUnpaintedLB";              //$NON-NLS-1$
    public static final String CARPET_HEAVY_ON_CONCRETE_CH                 =
                                                           "CarpetHeavyOnConcreteCH";                             //$NON-NLS-1$
    public static final String CONCRETE_BLOCK_PAINTED_CH                   =
                                                         "ConcreteBlockPaintedCH";                                //$NON-NLS-1$
    public static final String CONCRETE_BLOCK_UNPAINTED_CH                 =
                                                           "ConcreteBlockUnpaintedCH";                            //$NON-NLS-1$
    public static final String PLASTER_ON_LATHE_CH                         = "PlasterOnLathCH";                   //$NON-NLS-1$
    public static final String POURED_CONCRETE_PAINTED_LB                  =
                                                          "PouredConcretePaintedLB";                              //$NON-NLS-1$
    public static final String POURED_CONCRETE_UNPAINTED_LB                =
                                                            "PouredConcreteUnpaintedLB";                          //$NON-NLS-1$
    public static final String RIGID                                       = "Rigid";                             //$NON-NLS-1$
    public static final String VELOUR_TEN_OZ_PER_YARD_SQR_TOUCHING_WALL_CH =
                                                                           "VelourTenOzPerYardSqrTouchingWallCH"; //$NON-NLS-1$

    // Get the list of available Surface Materials, presented alphabetically,
    // but currently using the common abbreviated names.
    public static List< String > getSurfaceMaterials() {
        final ArrayList< String > surfaceMaterials = new ArrayList<>();

        surfaceMaterials.add( SurfaceMaterialNames.ACOUSTIC_TILE_ON_RIGID_SURF_KF );
        surfaceMaterials.add( SurfaceMaterialNames.BRICK_WALL_PAINTED_LB );
        surfaceMaterials.add( SurfaceMaterialNames.BRICK_WALL_UNPAINTED_LB );
        surfaceMaterials.add( SurfaceMaterialNames.CARPET_HEAVY_ON_CONCRETE_CH );
        surfaceMaterials.add( SurfaceMaterialNames.CONCRETE_BLOCK_PAINTED_CH );
        surfaceMaterials.add( SurfaceMaterialNames.CONCRETE_BLOCK_UNPAINTED_CH );
        surfaceMaterials.add( SurfaceMaterialNames.PLASTER_ON_LATHE_CH );
        surfaceMaterials.add( SurfaceMaterialNames.POURED_CONCRETE_PAINTED_LB );
        surfaceMaterials.add( SurfaceMaterialNames.POURED_CONCRETE_UNPAINTED_LB );
        surfaceMaterials.add( SurfaceMaterialNames.RIGID );
        surfaceMaterials.add( SurfaceMaterialNames.VELOUR_TEN_OZ_PER_YARD_SQR_TOUCHING_WALL_CH );

        return surfaceMaterials;
    }

    // NOTE: The constructor is disabled, as this is a static class.
    private SurfaceMaterialNames() {}

}
