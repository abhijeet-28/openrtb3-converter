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

package net.media.template;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

/** @author shiva.b */
public class MacroProcessor {
  private static final Pattern OPEN_RTB_TOKEN_PATTERN =
      Pattern.compile("\\$\\{(?<macro>.*?)(:(?<algo>.*?)){0,1}\\}");
  private static final String ALGO = "algo";
  private static final String MACRO = "macro";
  public static Template.TokenProvider TOKEN_PROVIDER =
      Template.getTokenProviderByGroupNames(Arrays.asList(MACRO, ALGO));
  private static EncodeTemplate.EncoderProvider ENCODER_PROVIDER =
      EncodeTemplate.getEncoderProviderByGroupName(ALGO);

  public static Template getOpenRtbMacroProcessor(String text) {
    if (Objects.isNull(text)) return tokenValue -> "";
    return new EncodeTemplate(
        text,
        OPEN_RTB_TOKEN_PATTERN,
        TOKEN_PROVIDER,
        ENCODER_PROVIDER,
      Template.Token::getTextValue);
  }

  public static final Template.TokenValue getTwoXToken() {
    return token -> {
      String macro = token.getGroup(MACRO);
      return MacroMapper.getTwoXMacro(MacroMapper.macroBuilder(macro));
    };
  }

  public static final Template.TokenValue getThreeXToken() {
    return token -> {
      String macro = token.getGroup(MACRO);
      return MacroMapper.getThreeXMacro(MacroMapper.macroBuilder(macro));
    };
  }

  public static final Template.TokenValue getBannerFields(net.media.openrtb3.Banner banner) {
    return token -> {
      String macro = token.getGroup(MACRO);
      switch (macro) {
        case "BANNER_URL":
          return banner.getImg();
        default:
          return "";
      }
    };
  }
}
