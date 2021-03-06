/*
 * Copyright  2019 - present. IAB Tech Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.media.converters.request30toRequest24;

import net.media.config.Config;
import net.media.exceptions.OpenRtbConverterException;
import net.media.openrtb25.request.Banner;
import net.media.openrtb25.request.Format;
import net.media.openrtb3.DisplayPlacement;
import net.media.utils.CommonConstants;
import net.media.utils.Provider;

import static java.util.Objects.nonNull;
import static net.media.utils.ExtUtils.putToExt;

/** Created by rajat.go on 03/04/19. */
public class DisplayPlacementToBannerConverter
    extends net.media.converters.request30toRequest25.DisplayPlacementToBannerConverter {

  public void enhance(
      DisplayPlacement displayPlacement, Banner banner, Config config, Provider converterProvider)
      throws OpenRtbConverterException {
    if (displayPlacement == null || banner == null) {
      return;
    }
    super.enhance(displayPlacement, banner, config, converterProvider);
    putToExt(banner::getVcm, banner.getExt(), CommonConstants.VCM, banner::setExt);
    banner.setVcm(null);
    if (nonNull(banner.getFormat())) {
      for (Format format : banner.getFormat()) {
        putToExt(format::getWratio, format.getExt(), CommonConstants.WRATIO, format::setExt);
        format.setWratio(null);
        putToExt(format::getHratio, format.getExt(), CommonConstants.HRATIO, format::setExt);
        format.setHratio(null);
        putToExt(format::getWmin, format.getExt(), CommonConstants.WMIN, format::setExt);
        format.setWmin(null);
      }
    }
  }
}
