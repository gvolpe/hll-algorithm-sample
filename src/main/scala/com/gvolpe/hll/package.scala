package com.gvolpe

import net.ruippeixotog.scalascraper.browser.Browser

package object hll {

  implicit class BrowserHttpProxy(browser: Browser) {
    def withHttpProxy(host: String, port: Int): Browser = {
      System.setProperty("http.proxyHost", host)
      System.setProperty("http.proxyPort", String.valueOf(port))
      System.setProperty("https.proxyHost", host)
      System.setProperty("https.proxyPort", String.valueOf(port))
      browser
    }
  }

}
