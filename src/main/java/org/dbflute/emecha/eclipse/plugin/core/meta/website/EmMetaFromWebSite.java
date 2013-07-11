package org.dbflute.emecha.eclipse.plugin.core.meta.website;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author jflute
 * @since 0.1.0 (2007/09/19 Wednesday)
 */
public class EmMetaFromWebSite {

    protected String urlString = "http://dbflute.seasar.org/meta/emecha.html";
    protected Properties prop;

    public EmMetaFromWebSite() {
    }

    public void loadMeta() {
        InputStream ins = null;
        try {
            prop = new Properties();
            final URL url = new URL(urlString);
            ins = url.openStream();
            prop.load(ins);
        } catch (IOException e) {
            throw new IllegalStateException("The url threw the IO exception: url=" + urlString, e);
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public String getLatestVersionDBFlute() {
        return getMetaValue("latest.version.dbflute");
    }

    public String getLatestSnapshotVersionDBFlute() {
        return getMetaValue("latest.snapshot.version.dbflute");
    }

    public String buildDownloadUrlDBFlute(String downloadVersion) {
        final String downloadTemplateUrlDBFlute = getDownloadTemplateUrlDBFlute();
        return downloadTemplateUrlDBFlute.replace("${downloadVersion}", downloadVersion);
    }

    public String getDownloadTemplateUrlDBFlute() {
        return getMetaValue("download.template.url.dbflute");
    }

    protected String getMetaValue(String key) {
        return prop.getProperty(key);
    }
}
