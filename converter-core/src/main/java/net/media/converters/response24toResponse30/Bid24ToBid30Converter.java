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

package net.media.converters.response24toResponse30;

import net.media.config.Config;
import net.media.converters.response25toresponse30.Bid25ToBid30Converter;
import net.media.exceptions.OpenRtbConverterException;
import net.media.openrtb25.response.Bid;
import net.media.utils.CommonConstants;
import net.media.utils.Provider;

import java.util.ArrayList;
import java.util.List;

import static net.media.utils.ExtUtils.fetchFromExt;
import static net.media.utils.ExtUtils.removeFromExt;

/** Created by rajat.go on 03/04/19. */
public class Bid24ToBid30Converter extends Bid25ToBid30Converter {

  private static final List<String> extraFieldsInExt = new ArrayList<>();

  static {
    extraFieldsInExt.add(CommonConstants.BURL);
    extraFieldsInExt.add(CommonConstants.LURL);
    extraFieldsInExt.add(CommonConstants.TACTIC);
    extraFieldsInExt.add(CommonConstants.LANGUAGE);
    extraFieldsInExt.add(CommonConstants.WRATIO);
    extraFieldsInExt.add(CommonConstants.HRATIO);
  }

  public void enhance(
      Bid source, net.media.openrtb3.Bid target, Config config, Provider converterProvider)
      throws OpenRtbConverterException {
    if (source == null || target == null) {
      return;
    }

    fetchFromExt(
      source::setBurl,
      source.getExt(),
      CommonConstants.BURL,
      "Failed while mapping burl from bid");
    fetchFromExt(
      source::setLurl,
      source.getExt(),
      CommonConstants.LURL,
      "Failed while mapping lurl from bid");
    fetchFromExt(
      source::setTactic,
      source.getExt(),
      CommonConstants.TACTIC,
      "Failed while mapping tactic from bid");
    fetchFromExt(
      source::setLanguage,
      source.getExt(),
      CommonConstants.LANGUAGE,
      "Failed while mapping language from bid");
    fetchFromExt(
      source::setWratio,
      source.getExt(),
      CommonConstants.WRATIO,
      "Failed while mapping wratio from bid");
    fetchFromExt(
      source::setHratio,
      source.getExt(),
      CommonConstants.HRATIO,
      "Failed while mapping hratio from bid");
    super.enhance(source, target, config, converterProvider);
    removeFromExt(target.getExt(), extraFieldsInExt);
  }
}
