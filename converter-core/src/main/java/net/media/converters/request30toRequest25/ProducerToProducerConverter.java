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

package net.media.converters.request30toRequest25;

import net.media.config.Config;
import net.media.converters.Converter;
import net.media.openrtb3.Producer;
import net.media.utils.CollectionUtils;
import net.media.utils.CommonConstants;
import net.media.utils.Provider;

import java.util.HashMap;
import java.util.Map;

import static net.media.utils.ExtUtils.putToExt;

public class ProducerToProducerConverter
    implements Converter<Producer, net.media.openrtb25.request.Producer> {

  @Override
  public net.media.openrtb25.request.Producer map(
      Producer source, Config config, Provider converterProvider) {
    if (source == null) {
      return null;
    }

    net.media.openrtb25.request.Producer producer1 = new net.media.openrtb25.request.Producer();

    enhance(source, producer1, config, converterProvider);

    return producer1;
  }

  @Override
  public void enhance(
      Producer source,
      net.media.openrtb25.request.Producer target,
      Config config,
      Provider converterProvider) {
    if (source == null || target == null) {
      return;
    }
    target.setId(source.getId());
    target.setName(source.getName());
    target.setCat(CollectionUtils.copyCollection(source.getCat(), config));
    target.setDomain(source.getDomain());
    Map<String, Object> map = source.getExt();
    if (map != null) {
      target.setExt(new HashMap<>(map));
    }
    putToExt(source::getCattax, target.getExt(), CommonConstants.CATTAX, target::setExt);
  }
}
