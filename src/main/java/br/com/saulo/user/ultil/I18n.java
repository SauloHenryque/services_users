
package br.com.saulo.user.ultil;

import static java.text.MessageFormat.format;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.junit.Ignore;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.thiagonego.alfred.object.Objeto;
import com.google.common.base.Charsets;

import lombok.extern.slf4j.Slf4j;

@Ignore
@Slf4j
public class I18n {

     private static final String ISO_8859_1 = "ISO-8859-1";

     public static final String LOCALE_PARAM = "lang";

     public static final String LOCALE_DEFAULT = "pt_BR";

     public static String getMessage(String key) {

          return getMessage(key, new Object[] {});

     }

     public static String getMessage(String key, Object[] args) {

          HttpServletRequest request = null;
          String msg = null;
          try {

               request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

          } catch (Exception e) {
          }

          msg = getMessage(key, request, args);

          return msg;
     }

     public static String getMessage(String key, HttpServletRequest request, Object[] args) {

          try {

               ResourceBundle bundle = ResourceBundle.getBundle("messages", getLocale(request));

               String msg = bundle.getString(tratarKey(key));
               if (Objeto.notBlank(msg) && args != null && args.length != 0) {
                    msg = format(msg, args);
               }

               if (Objeto.notBlank(msg)) {
                    return new String(msg.getBytes(ISO_8859_1), Charsets.UTF_8);
               }

          } catch (Exception e) {
               log.error(e.getMessage(), e);
          }

          return null;

     }

     public static Locale getLocale(HttpServletRequest request) {

          Locale locale = StringUtils.parseLocaleString(LOCALE_DEFAULT);
          if (request != null) {

               String param = (String) Objeto.or(request.getHeader(LOCALE_PARAM), request.getParameter(LOCALE_PARAM));
               if (Objeto.notBlank(param)) {

                    locale = StringUtils.parseLocaleString(param);

               }

          }

          return locale;

     }

     public static String tratarKey(String key) {

          if (Objeto.notBlank(key)) {
               key = key.trim();
               key = key.replaceAll("\\{\\{\\{", "");
               key = key.replaceAll("\\}\\}\\}", "");
          }

          return key;

     }

}