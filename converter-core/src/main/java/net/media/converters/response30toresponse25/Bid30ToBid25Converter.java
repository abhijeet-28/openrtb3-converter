/*
 * Copyright  2019 - present. MEDIA.NET ADVERTISING FZ-LLC
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

package net.media.converters.response30toresponse25;

import net.media.config.Config;
import net.media.converters.Converter;
import net.media.driver.Conversion;
import net.media.exceptions.OpenRtbConverterException;
import net.media.openrtb25.response.Bid;
import net.media.openrtb3.Media;
import net.media.template.MacroMapper;
import net.media.utils.CommonConstants;
import net.media.utils.MapUtils;
import net.media.utils.Provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static net.media.utils.ExtUtils.fetchFromExt;
import static net.media.utils.ExtUtils.putToExt;
import static net.media.utils.ExtUtils.removeFromExt;

public class Bid30ToBid25Converter implements Converter<net.media.openrtb3.Bid, Bid> {

  private static final List<String> extraFieldsInExt = new ArrayList<>();
  static {
    extraFieldsInExt.add(CommonConstants.PROTOCOL);
  }

  @Override
  public Bid map(net.media.openrtb3.Bid source, Config config, Provider converterProvider)
      throws OpenRtbConverterException {
    Bid bid = new Bid();
    enhance(source, bid, config, converterProvider);
    return bid;
  }

  @Override
  public void enhance(
      net.media.openrtb3.Bid source, Bid target, Config config, Provider converterProvider)
      throws OpenRtbConverterException {
    Converter<Media, Bid> mediaBidConverter =
        converterProvider.fetch(new Conversion<>(Media.class, Bid.class));

    if (source == null && target == null && config == null) {
      return;
    }
    if (source != null) {
      Map<String, Object> map = source.getExt();
      if (map != null) {
        target.setExt(new HashMap<>(map));
      }
      target.setId(source.getId());
      if (source.getPrice() != null) {
        target.setPrice(source.getPrice());
      }
      target.setImpid(source.getItem());
      target.setDealid(source.getDeal());
      target.setNurl(source.getPurl());
      target.setCid(source.getCid());
      target.setExp(source.getExp());
      target.setBurl(source.getBurl());
      target.setLurl(source.getLurl());
      target.setTactic(source.getTactic());
      if (isNull(target.getExt())) {
        target.setExt(new HashMap<>());
      }
      target.setAdid(source.getMid());
      target.setExt(putToExt(source::getMacro, target.getExt(), CommonConstants.MACRO));
      mediaBidConverter.enhance(source.getMedia(), target, config, converterProvider);
      MacroMapper.macroReplaceTwoX(target);
      fetchFromExt(target::setProtocol, source.getExt(), CommonConstants.PROTOCOL, "error while mapping protocol from Bid.ext");
    }
    removeFromExt(target.getExt(), extraFieldsInExt);
  }
}
