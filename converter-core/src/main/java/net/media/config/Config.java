package net.media.config;

import net.media.enums.AdType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * ORTB converter configuration
 *
 * @author shiva.b
 */


@NoArgsConstructor
@Data
public class Config {

  private static final boolean DEFAULT_NATIVE_REQUEST_AS_STRING = true;

  private static final boolean DEFAULT_DISABLE_CLONING = false;

  private static final boolean DEFAULT_VALIDATE = true;

  private String bannerTemplate;

  private Boolean nativeRequestAsString;

  private AdType adType;

  private Boolean validate;

  private Boolean disableCloning;

  public Config(Config oldConfig) {
    this.bannerTemplate = oldConfig.bannerTemplate;
    this.nativeRequestAsString = oldConfig.nativeRequestAsString;
    this.adType = oldConfig.adType;
    this.validate = oldConfig.validate;
    this.disableCloning = oldConfig.disableCloning;
  }

  /**
   * @param config
   */
  public void updateEmptyFields(Config config) {
    this.bannerTemplate = isEmpty(this.bannerTemplate) ? config.bannerTemplate : this
      .bannerTemplate;
    this.nativeRequestAsString = isNull(this.nativeRequestAsString) ? config
      .nativeRequestAsString : this.nativeRequestAsString;
    this.adType = isNull(this.adType) ? config.adType : this.adType;
    this.validate = isNull(this.validate) ? config.validate : this.validate;
    this.disableCloning = isNull(this.disableCloning) ? config.getDisableCloning() : this
      .disableCloning;
  }

  public Boolean getNativeRequestAsString() {
    return nonNull(nativeRequestAsString) ? nativeRequestAsString :
      DEFAULT_NATIVE_REQUEST_AS_STRING;
  }

  public Boolean getValidate() {
    return nonNull(validate) ? validate : DEFAULT_VALIDATE;
  }

  public Boolean isCloningDisabled() {
    return nonNull(disableCloning) ? disableCloning : DEFAULT_DISABLE_CLONING;
  }

  /**
   * Read config object stored in JSON format from <code>String</code>
   *
   * @param content of config
   * @return config
   * @throws IOException error
   */
  public static Config fromJSON(String content) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromJSON(content, Config.class);
  }

  /**
   * Read config object stored in JSON format from <code>InputStream</code>
   *
   * @param inputStream object
   * @return config
   * @throws IOException error
   */
  public static Config fromJSON(InputStream inputStream) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromJSON(inputStream, Config.class);
  }

  /**
   * Read config object stored in JSON format from <code>File</code>
   *
   * @param file object
   * @param classLoader class loader
   * @return config
   * @throws IOException error
   */
  public static Config fromJSON(File file, ClassLoader classLoader) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromJSON(file, Config.class, classLoader);
  }

  /**
   * Read config object stored in JSON format from <code>File</code>
   *
   * @param file object
   * @return config
   * @throws IOException error
   */
  public static Config fromJSON(File file) throws IOException {
    return fromJSON(file, null);
  }

  /**
   * Read config object stored in JSON format from <code>URL</code>
   *
   * @param url object
   * @return config
   * @throws IOException error
   */
  public static Config fromJSON(URL url) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromJSON(url, Config.class);
  }

  /**
   * Read config object stored in JSON format from <code>Reader</code>
   *
   * @param reader object
   * @return config
   * @throws IOException error
   */
  public static Config fromJSON(Reader reader) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromJSON(reader, Config.class);
  }

  /**
   * Convert current configuration to JSON format
   *
   * @return config in json format
   * @throws IOException error
   */
  public String toJSON() throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.toJSON(this);
  }

  /**
   * Read config object stored in YAML format from <code>String</code>
   *
   * @param content of config
   * @return config
   * @throws IOException error
   */
  public static Config fromYAML(String content) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromYAML(content, Config.class);
  }

  /**
   * Read config object stored in YAML format from <code>InputStream</code>
   *
   * @param inputStream object
   * @return config
   * @throws IOException error
   */
  public static Config fromYAML(InputStream inputStream) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromYAML(inputStream, Config.class);
  }

  /**
   * Read config object stored in YAML format from <code>File</code>
   *
   * @param file object
   * @return config
   * @throws IOException error
   */
  public static Config fromYAML(File file) throws IOException {
    return fromYAML(file, null);
  }

  public static Config fromYAML(File file, ClassLoader classLoader) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromYAML(file, Config.class, classLoader);
  }

  /**
   * Read config object stored in YAML format from <code>URL</code>
   *
   * @param url object
   * @return config
   * @throws IOException error
   */
  public static Config fromYAML(URL url) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromYAML(url, Config.class);
  }

  /**
   * Read config object stored in YAML format from <code>Reader</code>
   *
   * @param reader object
   * @return config
   * @throws IOException error
   */
  public static Config fromYAML(Reader reader) throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.fromYAML(reader, Config.class);
  }

  /**
   * Convert current configuration to YAML format
   *
   * @return config in yaml format
   * @throws IOException error
   */
  public String toYAML() throws IOException {
    ConfigSupport support = new ConfigSupport();
    return support.toYAML(this);
  }

}
