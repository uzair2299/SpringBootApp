package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.config.PropService;
import com.example.SprintBootAppWithSQL.dto.JwtDto;
import com.example.SprintBootAppWithSQL.dto.LoginDto;
import com.example.SprintBootAppWithSQL.dto.UserDto;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.services.UserService;
import com.example.SprintBootAppWithSQL.services.jwt.jwtImpl;
import com.sun.xml.bind.v2.TODO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@RestController
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    jwtImpl jwt;


    @Autowired
    PropService propService;
    @Autowired
    UserService userService;


    //TODO
    // need to handle
    // AccountExpiredException - This exception is thrown when the user account has expired.
    // CredentialsExpiredException - This exception is thrown when the user's credentials have expired and need to be updated.
    // AuthenticationCredentialsNotFoundException - This exception is thrown when the authentication credentials are missing (e.g. the user didn't provide a password).
    // AuthenticationServiceException - This exception is thrown when there is an error in the authentication service (e.g. the user database is down).


    @PostMapping("/api/v1/login/")
    public ResponseEntity<JwtDto> login(@RequestBody @Valid LoginDto user) {
        try {
           // sendEmail();
            logger.info("hello");
            log.info("update url = " +  propService.getBaseUrl());
            propService.setBaseUrl(user.getUserName());
            LoginDto result = userService.getUserByUserName(user);
            Map<String, Object> claims = new HashMap<>();
            claims.put("userName","Testing");
            return new ResponseEntity<>(jwt.createToken(claims), HttpStatus.OK);
        } catch (BadCredentialsException | DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtDto().error(e.getMessage()));
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(new JwtDto().error(e.getMessage()));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JwtDto().error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    private void sendEmail(){
        // Sender's email address and password
        String senderEmail = "uzairanwar2299@gmail.com";
        String senderPassword = "azarlecarhmiqrqu";

        // Recipient's email address
        String recipientEmail = "uzairanwar2299@gmail.com";

        // Email subject and content
        String emailSubject = "Hello from JavaMail";
        String emailContent = "This is the email content.";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(emailSubject);
            //message.setText(emailContent);

            //confirm your email
            String t1 = "\n" +
                    "<!doctype html>\n" +
                    "<html lang=\"en-US\">\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                    "    <!--100% body table-->\n" +
                    "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                    "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                    "        <tr>\n" +
                    "            <td>\n" +
                    "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                    "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"text-align:center;\">\n" +
                    "                          <a href=\"https://rakeshmandal.com\" title=\"logo\" target=\"_blank\">\n" +
                    "                            <img width=\"60\" src=\"https://i.ibb.co/hL4XZp2/android-chrome-192x192.png\" title=\"logo\" alt=\"logo\">\n" +
                    "                          </a>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td>\n" +
                    "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                    "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"padding:0 35px;\">\n" +
                    "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\n" +
                    "                                            requested to reset your password</h1>\n" +
                    "                                        <span\n" +
                    "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                    "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
                    "                                            We cannot simply send you your old password. A unique link to reset your\n" +
                    "                                            password has been generated for you. To reset your password, click the\n" +
                    "                                            following link and follow the instructions.\n" +
                    "                                        </p>\n" +
                    "                                        <a href=\"javascript:void(0);\"\n" +
                    "                                            style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Reset\n" +
                    "                                            Password</a>\n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                    "                                </tr>\n" +
                    "                            </table>\n" +
                    "                        </td>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"text-align:center;\">\n" +
                    "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.rakeshmandal.com</strong></p>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                </table>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "    <!--/100% body table-->\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";



            String t2 = "<!doctype html>\n" +
                    "<html>\n" +
                    "  <head>\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width\" />\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                    "    <title>Simple Transactional Email</title>\n" +
                    "    <style>\n" +
                    "      /* -------------------------------------\n" +
                    "          GLOBAL RESETS\n" +
                    "      ------------------------------------- */\n" +
                    "      img {\n" +
                    "        border: none;\n" +
                    "        -ms-interpolation-mode: bicubic;\n" +
                    "        max-width: 100%; }\n" +
                    "      body {\n" +
                    "        background-color: #f6f6f6;\n" +
                    "        font-family: sans-serif;\n" +
                    "        -webkit-font-smoothing: antialiased;\n" +
                    "        font-size: 14px;\n" +
                    "        line-height: 1.4;\n" +
                    "        margin: 0;\n" +
                    "        padding: 0; \n" +
                    "        -ms-text-size-adjust: 100%;\n" +
                    "        -webkit-text-size-adjust: 100%; }\n" +
                    "      table {\n" +
                    "        border-collapse: separate;\n" +
                    "        mso-table-lspace: 0pt;\n" +
                    "        mso-table-rspace: 0pt;\n" +
                    "        width: 100%; }\n" +
                    "        table td {\n" +
                    "          font-family: sans-serif;\n" +
                    "          font-size: 14px;\n" +
                    "          vertical-align: top; }\n" +
                    "      /* -------------------------------------\n" +
                    "          BODY & CONTAINER\n" +
                    "      ------------------------------------- */\n" +
                    "      .body {\n" +
                    "        background-color: #f6f6f6;\n" +
                    "        width: 100%; }\n" +
                    "      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
                    "      .container {\n" +
                    "        display: block;\n" +
                    "        Margin: 0 auto !important;\n" +
                    "        /* makes it centered */\n" +
                    "        max-width: 580px;\n" +
                    "        padding: 10px;\n" +
                    "        width: 580px; }\n" +
                    "      /* This should also be a block element, so that it will fill 100% of the .container */\n" +
                    "      .content {\n" +
                    "        box-sizing: border-box;\n" +
                    "        display: block;\n" +
                    "        Margin: 0 auto;\n" +
                    "        max-width: 580px;\n" +
                    "        padding: 10px; }\n" +
                    "      /* -------------------------------------\n" +
                    "          HEADER, FOOTER, MAIN\n" +
                    "      ------------------------------------- */\n" +
                    "      .main {\n" +
                    "        background: #fff;\n" +
                    "        border-radius: 3px;\n" +
                    "        width: 100%; }\n" +
                    "      .wrapper {\n" +
                    "        box-sizing: border-box;\n" +
                    "        padding: 20px; }\n" +
                    "      .footer {\n" +
                    "        clear: both;\n" +
                    "        padding-top: 10px;\n" +
                    "        text-align: center;\n" +
                    "        width: 100%; }\n" +
                    "        .footer td,\n" +
                    "        .footer p,\n" +
                    "        .footer span,\n" +
                    "        .footer a {\n" +
                    "          color: #999999;\n" +
                    "          font-size: 12px;\n" +
                    "          text-align: center; }\n" +
                    "      /* -------------------------------------\n" +
                    "          TYPOGRAPHY\n" +
                    "      ------------------------------------- */\n" +
                    "      h1,\n" +
                    "      h2,\n" +
                    "      h3,\n" +
                    "      h4 {\n" +
                    "        color: #000000;\n" +
                    "        font-family: sans-serif;\n" +
                    "        font-weight: 400;\n" +
                    "        line-height: 1.4;\n" +
                    "        margin: 0;\n" +
                    "        Margin-bottom: 30px; }\n" +
                    "      h1 {\n" +
                    "        font-size: 35px;\n" +
                    "        font-weight: 300;\n" +
                    "        text-align: center;\n" +
                    "        text-transform: capitalize; }\n" +
                    "      p,\n" +
                    "      ul,\n" +
                    "      ol {\n" +
                    "        font-family: sans-serif;\n" +
                    "        font-size: 14px;\n" +
                    "        font-weight: normal;\n" +
                    "        margin: 0;\n" +
                    "        Margin-bottom: 15px; }\n" +
                    "        p li,\n" +
                    "        ul li,\n" +
                    "        ol li {\n" +
                    "          list-style-position: inside;\n" +
                    "          margin-left: 5px; }\n" +
                    "      a {\n" +
                    "        color: #3498db;\n" +
                    "        text-decoration: underline; }\n" +
                    "      /* -------------------------------------\n" +
                    "          BUTTONS\n" +
                    "      ------------------------------------- */\n" +
                    "      .btn {\n" +
                    "        box-sizing: border-box;\n" +
                    "        width: 100%; }\n" +
                    "        .btn > tbody > tr > td {\n" +
                    "          padding-bottom: 15px; }\n" +
                    "        .btn table {\n" +
                    "          width: auto; }\n" +
                    "        .btn table td {\n" +
                    "          background-color: #ffffff;\n" +
                    "          border-radius: 5px;\n" +
                    "          text-align: center; }\n" +
                    "        .btn a {\n" +
                    "          background-color: #ffffff;\n" +
                    "          border: solid 1px #3498db;\n" +
                    "          border-radius: 5px;\n" +
                    "          box-sizing: border-box;\n" +
                    "          color: #3498db;\n" +
                    "          cursor: pointer;\n" +
                    "          display: inline-block;\n" +
                    "          font-size: 14px;\n" +
                    "          font-weight: bold;\n" +
                    "          margin: 0;\n" +
                    "          padding: 12px 25px;\n" +
                    "          text-decoration: none;\n" +
                    "          text-transform: capitalize; }\n" +
                    "      .btn-primary table td {\n" +
                    "        background-color: #3498db; }\n" +
                    "      .btn-primary a {\n" +
                    "        background-color: #3498db;\n" +
                    "        border-color: #3498db;\n" +
                    "        color: #ffffff; }\n" +
                    "      /* -------------------------------------\n" +
                    "          OTHER STYLES THAT MIGHT BE USEFUL\n" +
                    "      ------------------------------------- */\n" +
                    "      .last {\n" +
                    "        margin-bottom: 0; }\n" +
                    "      .first {\n" +
                    "        margin-top: 0; }\n" +
                    "      .align-center {\n" +
                    "        text-align: center; }\n" +
                    "      .align-right {\n" +
                    "        text-align: right; }\n" +
                    "      .align-left {\n" +
                    "        text-align: left; }\n" +
                    "      .clear {\n" +
                    "        clear: both; }\n" +
                    "      .mt0 {\n" +
                    "        margin-top: 0; }\n" +
                    "      .mb0 {\n" +
                    "        margin-bottom: 0; }\n" +
                    "      .preheader {\n" +
                    "        color: transparent;\n" +
                    "        display: none;\n" +
                    "        height: 0;\n" +
                    "        max-height: 0;\n" +
                    "        max-width: 0;\n" +
                    "        opacity: 0;\n" +
                    "        overflow: hidden;\n" +
                    "        mso-hide: all;\n" +
                    "        visibility: hidden;\n" +
                    "        width: 0; }\n" +
                    "      .powered-by a {\n" +
                    "        text-decoration: none; }\n" +
                    "      hr {\n" +
                    "        border: 0;\n" +
                    "        border-bottom: 1px solid #f6f6f6;\n" +
                    "        Margin: 20px 0; }\n" +
                    "      /* -------------------------------------\n" +
                    "          RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                    "      ------------------------------------- */\n" +
                    "      @media only screen and (max-width: 620px) {\n" +
                    "        table[class=body] h1 {\n" +
                    "          font-size: 28px !important;\n" +
                    "          margin-bottom: 10px !important; }\n" +
                    "        table[class=body] p,\n" +
                    "        table[class=body] ul,\n" +
                    "        table[class=body] ol,\n" +
                    "        table[class=body] td,\n" +
                    "        table[class=body] span,\n" +
                    "        table[class=body] a {\n" +
                    "          font-size: 16px !important; }\n" +
                    "        table[class=body] .wrapper,\n" +
                    "        table[class=body] .article {\n" +
                    "          padding: 10px !important; }\n" +
                    "        table[class=body] .content {\n" +
                    "          padding: 0 !important; }\n" +
                    "        table[class=body] .container {\n" +
                    "          padding: 0 !important;\n" +
                    "          width: 100% !important; }\n" +
                    "        table[class=body] .main {\n" +
                    "          border-left-width: 0 !important;\n" +
                    "          border-radius: 0 !important;\n" +
                    "          border-right-width: 0 !important; }\n" +
                    "        table[class=body] .btn table {\n" +
                    "          width: 100% !important; }\n" +
                    "        table[class=body] .btn a {\n" +
                    "          width: 100% !important; }\n" +
                    "        table[class=body] .img-responsive {\n" +
                    "          height: auto !important;\n" +
                    "          max-width: 100% !important;\n" +
                    "          width: auto !important; }}\n" +
                    "      @media all {\n" +
                    "        .ExternalClass {\n" +
                    "          width: 100%; }\n" +
                    "        .ExternalClass,\n" +
                    "        .ExternalClass p,\n" +
                    "        .ExternalClass span,\n" +
                    "        .ExternalClass font,\n" +
                    "        .ExternalClass td,\n" +
                    "        .ExternalClass div {\n" +
                    "          line-height: 100%; }\n" +
                    "        .apple-link a {\n" +
                    "          color: inherit !important;\n" +
                    "          font-family: inherit !important;\n" +
                    "          font-size: inherit !important;\n" +
                    "          font-weight: inherit !important;\n" +
                    "          line-height: inherit !important;\n" +
                    "          text-decoration: none !important; } \n" +
                    "        .btn-primary table td:hover {\n" +
                    "          background-color: #34495e !important; }\n" +
                    "        .btn-primary a:hover {\n" +
                    "          background-color: #34495e !important;\n" +
                    "          border-color: #34495e !important; } }\n" +
                    "    </style>\n" +
                    "  </head>\n" +
                    "  <body class=\"\">\n" +
                    "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\n" +
                    "      <tr>\n" +
                    "        <td>&nbsp;</td>\n" +
                    "        <td class=\"container\">\n" +
                    "          <div class=\"content\">\n" +
                    "            <span class=\"preheader\">Subscribe to Coloured.com.ng mailing list</span>\n" +
                    "            <table class=\"main\">\n" +
                    "\n" +
                    "              <!-- START MAIN CONTENT AREA -->\n" +
                    "              <tr>\n" +
                    "                <td class=\"wrapper\">\n" +
                    "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                    <tr>\n" +
                    "                      <td>\n" +
                    "                        <h1>Confirm your email</h1>\n" +
                    "                        <h2>You are just one step away</h2>\n" +
                    "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\n" +
                    "                          <tbody>\n" +
                    "                            <tr>\n" +
                    "                              <td align=\"left\">\n" +
                    "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                                  <tbody>\n" +
                    "                                    <tr>\n" +
                    "                                      <td> <a href=\"http://htmlemail.io\" target=\"_blank\">confirm email</a> </td>\n" +
                    "                                    </tr>\n" +
                    "                                  </tbody>\n" +
                    "                                </table>\n" +
                    "                              </td>\n" +
                    "                            </tr>\n" +
                    "                          </tbody>\n" +
                    "                        </table>\n" +
                    "                        <p>If you received this email by mistake, simply delete it. You won't be subscribed if you don't click the confirmation link above.</p>\n" +
                    "      \n" +
                    "                      </td>\n" +
                    "                    </tr>\n" +
                    "                  </table>\n" +
                    "                </td>\n" +
                    "              </tr>\n" +
                    "\n" +
                    "            <!-- END MAIN CONTENT AREA -->\n" +
                    "            </table>\n" +
                    "\n" +
                    "            <!-- START FOOTER -->\n" +
                    "            <div class=\"footer\">\n" +
                    "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                <tr>\n" +
                    "                  <td class=\"content-block\">\n" +
                    "                    <span class=\"apple-link\">Coloured.com.ng | Feminism | Culture | Law | Feminists Rising</span>\n" +
                    "                    <br> Don't like these emails? <a href=\"#\">Unsubscribe</a>.\n" +
                    "                  </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                  <td class=\"content-block powered-by\">\n" +
                    "                    Powered by <a href=\"https://fb.me/jalasem\">Jalasem</a>.\n" +
                    "                  </td>\n" +
                    "                </tr>\n" +
                    "              </table>\n" +
                    "            </div>\n" +
                    "            <!-- END FOOTER -->\n" +
                    "            \n" +
                    "          <!-- END CENTERED WHITE CONTAINER -->\n" +
                    "          </div>\n" +
                    "        </td>\n" +
                    "        <td>&nbsp;</td>\n" +
                    "      </tr>\n" +
                    "    </table>\n" +
                    "  </body>\n" +
                    "</html>";

            String t3 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                    "  <head>\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                    "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                    "    <meta name=\"color-scheme\" content=\"light dark\" />\n" +
                    "    <meta name=\"supported-color-schemes\" content=\"light dark\" />\n" +
                    "    <title></title>\n" +
                    "    <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                    "    /* Base ------------------------------ */\n" +
                    "    \n" +
                    "    @import url(\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap\");\n" +
                    "    body {\n" +
                    "      width: 100% !important;\n" +
                    "      height: 100%;\n" +
                    "      margin: 0;\n" +
                    "      -webkit-text-size-adjust: none;\n" +
                    "    }\n" +
                    "    \n" +
                    "    a {\n" +
                    "      color: #3869D4;\n" +
                    "    }\n" +
                    "    \n" +
                    "    a img {\n" +
                    "      border: none;\n" +
                    "    }\n" +
                    "    \n" +
                    "    td {\n" +
                    "      word-break: break-word;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .preheader {\n" +
                    "      display: none !important;\n" +
                    "      visibility: hidden;\n" +
                    "      mso-hide: all;\n" +
                    "      font-size: 1px;\n" +
                    "      line-height: 1px;\n" +
                    "      max-height: 0;\n" +
                    "      max-width: 0;\n" +
                    "      opacity: 0;\n" +
                    "      overflow: hidden;\n" +
                    "    }\n" +
                    "    /* Type ------------------------------ */\n" +
                    "    \n" +
                    "    body,\n" +
                    "    td,\n" +
                    "    th {\n" +
                    "      font-family: \"Nunito Sans\", Helvetica, Arial, sans-serif;\n" +
                    "    }\n" +
                    "    \n" +
                    "    h1 {\n" +
                    "      margin-top: 0;\n" +
                    "      color: #333333;\n" +
                    "      font-size: 22px;\n" +
                    "      font-weight: bold;\n" +
                    "      text-align: left;\n" +
                    "    }\n" +
                    "    \n" +
                    "    h2 {\n" +
                    "      margin-top: 0;\n" +
                    "      color: #333333;\n" +
                    "      font-size: 16px;\n" +
                    "      font-weight: bold;\n" +
                    "      text-align: left;\n" +
                    "    }\n" +
                    "    \n" +
                    "    h3 {\n" +
                    "      margin-top: 0;\n" +
                    "      color: #333333;\n" +
                    "      font-size: 14px;\n" +
                    "      font-weight: bold;\n" +
                    "      text-align: left;\n" +
                    "    }\n" +
                    "    \n" +
                    "    td,\n" +
                    "    th {\n" +
                    "      font-size: 16px;\n" +
                    "    }\n" +
                    "    \n" +
                    "    p,\n" +
                    "    ul,\n" +
                    "    ol,\n" +
                    "    blockquote {\n" +
                    "      margin: .4em 0 1.1875em;\n" +
                    "      font-size: 16px;\n" +
                    "      line-height: 1.625;\n" +
                    "    }\n" +
                    "    \n" +
                    "    p.sub {\n" +
                    "      font-size: 13px;\n" +
                    "    }\n" +
                    "    /* Utilities ------------------------------ */\n" +
                    "    \n" +
                    "    .align-right {\n" +
                    "      text-align: right;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .align-left {\n" +
                    "      text-align: left;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .align-center {\n" +
                    "      text-align: center;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .u-margin-bottom-none {\n" +
                    "      margin-bottom: 0;\n" +
                    "    }\n" +
                    "    /* Buttons ------------------------------ */\n" +
                    "    \n" +
                    "    .button {\n" +
                    "      background-color: #3869D4;\n" +
                    "      border-top: 10px solid #3869D4;\n" +
                    "      border-right: 18px solid #3869D4;\n" +
                    "      border-bottom: 10px solid #3869D4;\n" +
                    "      border-left: 18px solid #3869D4;\n" +
                    "      display: inline-block;\n" +
                    "      color: #FFF;\n" +
                    "      text-decoration: none;\n" +
                    "      border-radius: 3px;\n" +
                    "      box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16);\n" +
                    "      -webkit-text-size-adjust: none;\n" +
                    "      box-sizing: border-box;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .button--green {\n" +
                    "      background-color: #22BC66;\n" +
                    "      border-top: 10px solid #22BC66;\n" +
                    "      border-right: 18px solid #22BC66;\n" +
                    "      border-bottom: 10px solid #22BC66;\n" +
                    "      border-left: 18px solid #22BC66;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .button--red {\n" +
                    "      background-color: #FF6136;\n" +
                    "      border-top: 10px solid #FF6136;\n" +
                    "      border-right: 18px solid #FF6136;\n" +
                    "      border-bottom: 10px solid #FF6136;\n" +
                    "      border-left: 18px solid #FF6136;\n" +
                    "    }\n" +
                    "    \n" +
                    "    @media only screen and (max-width: 500px) {\n" +
                    "      .button {\n" +
                    "        width: 100% !important;\n" +
                    "        text-align: center !important;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    /* Attribute list ------------------------------ */\n" +
                    "    \n" +
                    "    .attributes {\n" +
                    "      margin: 0 0 21px;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .attributes_content {\n" +
                    "      background-color: #F4F4F7;\n" +
                    "      padding: 16px;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .attributes_item {\n" +
                    "      padding: 0;\n" +
                    "    }\n" +
                    "    /* Related Items ------------------------------ */\n" +
                    "    \n" +
                    "    .related {\n" +
                    "      width: 100%;\n" +
                    "      margin: 0;\n" +
                    "      padding: 25px 0 0 0;\n" +
                    "      -premailer-width: 100%;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .related_item {\n" +
                    "      padding: 10px 0;\n" +
                    "      color: #CBCCCF;\n" +
                    "      font-size: 15px;\n" +
                    "      line-height: 18px;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .related_item-title {\n" +
                    "      display: block;\n" +
                    "      margin: .5em 0 0;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .related_item-thumb {\n" +
                    "      display: block;\n" +
                    "      padding-bottom: 10px;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .related_heading {\n" +
                    "      border-top: 1px solid #CBCCCF;\n" +
                    "      text-align: center;\n" +
                    "      padding: 25px 0 10px;\n" +
                    "    }\n" +
                    "    /* Discount Code ------------------------------ */\n" +
                    "    \n" +
                    "    .discount {\n" +
                    "      width: 100%;\n" +
                    "      margin: 0;\n" +
                    "      padding: 24px;\n" +
                    "      -premailer-width: 100%;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "      background-color: #F4F4F7;\n" +
                    "      border: 2px dashed #CBCCCF;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .discount_heading {\n" +
                    "      text-align: center;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .discount_body {\n" +
                    "      text-align: center;\n" +
                    "      font-size: 15px;\n" +
                    "    }\n" +
                    "    /* Social Icons ------------------------------ */\n" +
                    "    \n" +
                    "    .social {\n" +
                    "      width: auto;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .social td {\n" +
                    "      padding: 0;\n" +
                    "      width: auto;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .social_icon {\n" +
                    "      height: 20px;\n" +
                    "      margin: 0 8px 10px 8px;\n" +
                    "      padding: 0;\n" +
                    "    }\n" +
                    "    /* Data table ------------------------------ */\n" +
                    "    \n" +
                    "    .purchase {\n" +
                    "      width: 100%;\n" +
                    "      margin: 0;\n" +
                    "      padding: 35px 0;\n" +
                    "      -premailer-width: 100%;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .purchase_content {\n" +
                    "      width: 100%;\n" +
                    "      margin: 0;\n" +
                    "      padding: 25px 0 0 0;\n" +
                    "      -premailer-width: 100%;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .purchase_item {\n" +
                    "      padding: 10px 0;\n" +
                    "      color: #51545E;\n" +
                    "      font-size: 15px;\n" +
                    "      line-height: 18px;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .purchase_heading {\n" +
                    "      padding-bottom: 8px;\n" +
                    "      border-bottom: 1px solid #EAEAEC;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .purchase_heading p {\n" +
                    "      margin: 0;\n" +
                    "      color: #85878E;\n" +
                    "      font-size: 12px;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .purchase_footer {\n" +
                    "      padding-top: 15px;\n" +
                    "      border-top: 1px solid #EAEAEC;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .purchase_total {\n" +
                    "      margin: 0;\n" +
                    "      text-align: right;\n" +
                    "      font-weight: bold;\n" +
                    "      color: #333333;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .purchase_total--label {\n" +
                    "      padding: 0 15px 0 0;\n" +
                    "    }\n" +
                    "    \n" +
                    "    body {\n" +
                    "      background-color: #F2F4F6;\n" +
                    "      color: #51545E;\n" +
                    "    }\n" +
                    "    \n" +
                    "    p {\n" +
                    "      color: #51545E;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .email-wrapper {\n" +
                    "      width: 100%;\n" +
                    "      margin: 0;\n" +
                    "      padding: 0;\n" +
                    "      -premailer-width: 100%;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "      background-color: #F2F4F6;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .email-content {\n" +
                    "      width: 100%;\n" +
                    "      margin: 0;\n" +
                    "      padding: 0;\n" +
                    "      -premailer-width: 100%;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "    }\n" +
                    "    /* Masthead ----------------------- */\n" +
                    "    \n" +
                    "    .email-masthead {\n" +
                    "      padding: 25px 0;\n" +
                    "      text-align: center;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .email-masthead_logo {\n" +
                    "      width: 94px;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .email-masthead_name {\n" +
                    "      font-size: 16px;\n" +
                    "      font-weight: bold;\n" +
                    "      color: #A8AAAF;\n" +
                    "      text-decoration: none;\n" +
                    "      text-shadow: 0 1px 0 white;\n" +
                    "    }\n" +
                    "    /* Body ------------------------------ */\n" +
                    "    \n" +
                    "    .email-body {\n" +
                    "      width: 100%;\n" +
                    "      margin: 0;\n" +
                    "      padding: 0;\n" +
                    "      -premailer-width: 100%;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .email-body_inner {\n" +
                    "      width: 570px;\n" +
                    "      margin: 0 auto;\n" +
                    "      padding: 0;\n" +
                    "      -premailer-width: 570px;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "      background-color: #FFFFFF;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .email-footer {\n" +
                    "      width: 570px;\n" +
                    "      margin: 0 auto;\n" +
                    "      padding: 0;\n" +
                    "      -premailer-width: 570px;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "      text-align: center;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .email-footer p {\n" +
                    "      color: #A8AAAF;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .body-action {\n" +
                    "      width: 100%;\n" +
                    "      margin: 30px auto;\n" +
                    "      padding: 0;\n" +
                    "      -premailer-width: 100%;\n" +
                    "      -premailer-cellpadding: 0;\n" +
                    "      -premailer-cellspacing: 0;\n" +
                    "      text-align: center;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .body-sub {\n" +
                    "      margin-top: 25px;\n" +
                    "      padding-top: 25px;\n" +
                    "      border-top: 1px solid #EAEAEC;\n" +
                    "    }\n" +
                    "    \n" +
                    "    .content-cell {\n" +
                    "      padding: 45px;\n" +
                    "    }\n" +
                    "    /*Media Queries ------------------------------ */\n" +
                    "    \n" +
                    "    @media only screen and (max-width: 600px) {\n" +
                    "      .email-body_inner,\n" +
                    "      .email-footer {\n" +
                    "        width: 100% !important;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    \n" +
                    "    @media (prefers-color-scheme: dark) {\n" +
                    "      body,\n" +
                    "      .email-body,\n" +
                    "      .email-body_inner,\n" +
                    "      .email-content,\n" +
                    "      .email-wrapper,\n" +
                    "      .email-masthead,\n" +
                    "      .email-footer {\n" +
                    "        background-color: #333333 !important;\n" +
                    "        color: #FFF !important;\n" +
                    "      }\n" +
                    "      p,\n" +
                    "      ul,\n" +
                    "      ol,\n" +
                    "      blockquote,\n" +
                    "      h1,\n" +
                    "      h2,\n" +
                    "      h3,\n" +
                    "      span,\n" +
                    "      .purchase_item {\n" +
                    "        color: #FFF !important;\n" +
                    "      }\n" +
                    "      .attributes_content,\n" +
                    "      .discount {\n" +
                    "        background-color: #222 !important;\n" +
                    "      }\n" +
                    "      .email-masthead_name {\n" +
                    "        text-shadow: none !important;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    \n" +
                    "    :root {\n" +
                    "      color-scheme: light dark;\n" +
                    "      supported-color-schemes: light dark;\n" +
                    "    }\n" +
                    "    </style>\n" +
                    "    <!--[if mso]>\n" +
                    "    <style type=\"text/css\">\n" +
                    "      .f-fallback  {\n" +
                    "        font-family: Arial, sans-serif;\n" +
                    "      }\n" +
                    "    </style>\n" +
                    "  <![endif]-->\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <span class=\"preheader\">Thanks for trying out [Product Name]. We’ve pulled together some information and resources to help you get started.</span>\n" +
                    "    <table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                    "      <tr>\n" +
                    "        <td align=\"center\">\n" +
                    "          <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                    "            <tr>\n" +
                    "              <td class=\"email-masthead\">\n" +
                    "                <a href=\"https://example.com\" class=\"f-fallback email-masthead_name\">\n" +
                    "                [Product Name]\n" +
                    "              </a>\n" +
                    "              </td>\n" +
                    "            </tr>\n" +
                    "            <!-- Email Body -->\n" +
                    "            <tr>\n" +
                    "              <td class=\"email-body\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                    "                  <!-- Body content -->\n" +
                    "                  <tr>\n" +
                    "                    <td class=\"content-cell\">\n" +
                    "                      <div class=\"f-fallback\">\n" +
                    "                        <h1>Welcome, {{name}}!</h1>\n" +
                    "                        <p>Thanks for trying [Product Name]. We’re thrilled to have you on board. To get the most out of [Product Name], do this primary next step:</p>\n" +
                    "                        <!-- Action -->\n" +
                    "                        <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                    "                          <tr>\n" +
                    "                            <td align=\"center\">\n" +
                    "                              <!-- Border based button\n" +
                    "           https://litmus.com/blog/a-guide-to-bulletproof-buttons-in-email-design -->\n" +
                    "                              <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                    "                                <tr>\n" +
                    "                                  <td align=\"center\">\n" +
                    "                                    <a href=\"{{action_url}}\" class=\"f-fallback button\" target=\"_blank\">Do this Next</a>\n" +
                    "                                  </td>\n" +
                    "                                </tr>\n" +
                    "                              </table>\n" +
                    "                            </td>\n" +
                    "                          </tr>\n" +
                    "                        </table>\n" +
                    "                        <p>For reference, here's your login information:</p>\n" +
                    "                        <table class=\"attributes\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                    "                          <tr>\n" +
                    "                            <td class=\"attributes_content\">\n" +
                    "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                    "                                <tr>\n" +
                    "                                  <td class=\"attributes_item\">\n" +
                    "                                    <span class=\"f-fallback\">\n" +
                    "              <strong>Login Page:</strong> {{login_url}}\n" +
                    "            </span>\n" +
                    "                                  </td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                  <td class=\"attributes_item\">\n" +
                    "                                    <span class=\"f-fallback\">\n" +
                    "              <strong>Username:</strong> {{username}}\n" +
                    "            </span>\n" +
                    "                                  </td>\n" +
                    "                                </tr>\n" +
                    "                              </table>\n" +
                    "                            </td>\n" +
                    "                          </tr>\n" +
                    "                        </table>\n" +
                    "                        <p>You've started a {{trial_length}} day trial. You can upgrade to a paying account or cancel any time.</p>\n" +
                    "                        <table class=\"attributes\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                    "                          <tr>\n" +
                    "                            <td class=\"attributes_content\">\n" +
                    "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                    "                                <tr>\n" +
                    "                                  <td class=\"attributes_item\">\n" +
                    "                                    <span class=\"f-fallback\">\n" +
                    "              <strong>Trial Start Date:</strong> {{trial_start_date}}\n" +
                    "            </span>\n" +
                    "                                  </td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                  <td class=\"attributes_item\">\n" +
                    "                                    <span class=\"f-fallback\">\n" +
                    "              <strong>Trial End Date:</strong> {{trial_end_date}}\n" +
                    "            </span>\n" +
                    "                                  </td>\n" +
                    "                                </tr>\n" +
                    "                              </table>\n" +
                    "                            </td>\n" +
                    "                          </tr>\n" +
                    "                        </table>\n" +
                    "                        <p>If you have any questions, feel free to <a href=\"mailto:{{support_email}}\">email our customer success team</a>. (We're lightning quick at replying.) We also offer <a href=\"{{live_chat_url}}\">live chat</a> during business hours.</p>\n" +
                    "                        <p>Thanks,\n" +
                    "                          <br>[Sender Name] and the [Product Name] team</p>\n" +
                    "                        <p><strong>P.S.</strong> Need immediate help getting started? Check out our <a href=\"{{help_url}}\">help documentation</a>. Or, just reply to this email, the [Product Name] support team is always ready to help!</p>\n" +
                    "                        <!-- Sub copy -->\n" +
                    "                        <table class=\"body-sub\" role=\"presentation\">\n" +
                    "                          <tr>\n" +
                    "                            <td>\n" +
                    "                              <p class=\"f-fallback sub\">If you’re having trouble with the button above, copy and paste the URL below into your web browser.</p>\n" +
                    "                              <p class=\"f-fallback sub\">{{action_url}}</p>\n" +
                    "                            </td>\n" +
                    "                          </tr>\n" +
                    "                        </table>\n" +
                    "                      </div>\n" +
                    "                    </td>\n" +
                    "                  </tr>\n" +
                    "                </table>\n" +
                    "              </td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "              <td>\n" +
                    "                <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                    "                  <tr>\n" +
                    "                    <td class=\"content-cell\" align=\"center\">\n" +
                    "                      <p class=\"f-fallback sub align-center\">\n" +
                    "                        [Company Name, LLC]\n" +
                    "                        <br>1234 Street Rd.\n" +
                    "                        <br>Suite 1234\n" +
                    "                      </p>\n" +
                    "                    </td>\n" +
                    "                  </tr>\n" +
                    "                </table>\n" +
                    "              </td>\n" +
                    "            </tr>\n" +
                    "          </table>\n" +
                    "        </td>\n" +
                    "      </tr>\n" +
                    "    </table>\n" +
                    "  </body>\n" +
                    "</html>";

            String t4 = "\n" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title></title>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "<style type=\"text/css\">\n" +
                    "    /* FONTS */\n" +
                    "    @media screen {\n" +
                    "        @font-face {\n" +
                    "          font-family: 'Lato';\n" +
                    "          font-style: normal;\n" +
                    "          font-weight: 400;\n" +
                    "          src: local('Lato Regular'), local('Lato-Regular'), url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format('woff');\n" +
                    "        }\n" +
                    "\n" +
                    "        @font-face {\n" +
                    "          font-family: 'Lato';\n" +
                    "          font-style: normal;\n" +
                    "          font-weight: 700;\n" +
                    "          src: local('Lato Bold'), local('Lato-Bold'), url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format('woff');\n" +
                    "        }\n" +
                    "\n" +
                    "        @font-face {\n" +
                    "          font-family: 'Lato';\n" +
                    "          font-style: italic;\n" +
                    "          font-weight: 400;\n" +
                    "          src: local('Lato Italic'), local('Lato-Italic'), url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format('woff');\n" +
                    "        }\n" +
                    "\n" +
                    "        @font-face {\n" +
                    "          font-family: 'Lato';\n" +
                    "          font-style: italic;\n" +
                    "          font-weight: 700;\n" +
                    "          src: local('Lato Bold Italic'), local('Lato-BoldItalic'), url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format('woff');\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    /* CLIENT-SPECIFIC STYLES */\n" +
                    "    body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }\n" +
                    "    table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }\n" +
                    "    img { -ms-interpolation-mode: bicubic; }\n" +
                    "\n" +
                    "    /* RESET STYLES */\n" +
                    "    img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }\n" +
                    "    table { border-collapse: collapse !important; }\n" +
                    "    body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }\n" +
                    "\n" +
                    "    /* iOS BLUE LINKS */\n" +
                    "    a[x-apple-data-detectors] {\n" +
                    "        color: inherit !important;\n" +
                    "        text-decoration: none !important;\n" +
                    "        font-size: inherit !important;\n" +
                    "        font-family: inherit !important;\n" +
                    "        font-weight: inherit !important;\n" +
                    "        line-height: inherit !important;\n" +
                    "    }\n" +
                    "\n" +
                    "    /* MOBILE STYLES */\n" +
                    "    @media screen and (max-width:600px){\n" +
                    "        h1 {\n" +
                    "            font-size: 32px !important;\n" +
                    "            line-height: 32px !important;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    /* ANDROID CENTER FIX */\n" +
                    "    div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body style=\"background-color: #f3f5f7; margin: 0 !important; padding: 0 !important;\">\n" +
                    "\n" +
                    "<!-- HIDDEN PREHEADER TEXT -->\n" +
                    "<div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">\n" +
                    "    We're thrilled to have you here! Get ready to dive into your new account.\n" +
                    "</div>\n" +
                    "\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                    "    <!-- LOGO -->\n" +
                    "    <tr>\n" +
                    "        <td bgcolor=\"#33cabb\" align=\"center\">\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
                    "            <tr>\n" +
                    "            <td align=\"center\" valign=\"top\" width=\"600\">\n" +
                    "            <![endif]-->\n" +
                    "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" valign=\"top\" style=\"padding: 80px 10px 80px 10px;\">\n" +
                    "                        <a href=\"http://thetheme.io\" target=\"_blank\">\n" +
                    "                            <img alt=\"Logo\" src=\"../assets/img/logo-light-lg.png\" style=\"display: block; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; color: #ffffff; font-size: 18px;\" border=\"0\">\n" +
                    "                        </a>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            </td>\n" +
                    "            </tr>\n" +
                    "            </table>\n" +
                    "            <![endif]-->\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <!-- HERO -->\n" +
                    "    <tr>\n" +
                    "        <td bgcolor=\"#33cabb\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
                    "            <tr>\n" +
                    "            <td align=\"center\" valign=\"top\" width=\"600\">\n" +
                    "            <![endif]-->\n" +
                    "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                    "                <tr>\n" +
                    "                    <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">\n" +
                    "                      <h1 style=\"font-size: 42px; font-weight: 400; margin: 0;\">Welcome!</h1>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            </td>\n" +
                    "            </tr>\n" +
                    "            </table>\n" +
                    "            <![endif]-->\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <!-- COPY BLOCK -->\n" +
                    "    <tr>\n" +
                    "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
                    "            <tr>\n" +
                    "            <td align=\"center\" valign=\"top\" width=\"600\">\n" +
                    "            <![endif]-->\n" +
                    "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                    "              <!-- COPY -->\n" +
                    "              <tr>\n" +
                    "                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 25px;\">\n" +
                    "                  <p style=\"margin: 0;\">Thank you for signing up with TheAdmin! We hope you enjoy your time with us. Check your account and update your profile.</p>\n" +
                    "                </td>\n" +
                    "              </tr>\n" +
                    "              <!-- BULLETPROOF BUTTON -->\n" +
                    "              <tr>\n" +
                    "                <td bgcolor=\"#ffffff\" align=\"left\">\n" +
                    "                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                    "                    <tr>\n" +
                    "                      <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 60px 30px;\">\n" +
                    "                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                    "                          <tr>\n" +
                    "                              <td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#33cabb\"><a href=\"http://thetheme.io\" target=\"_blank\" style=\"font-size: 18px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 12px 50px; border-radius: 2px; border: 1px solid #33cabb; display: inline-block;\">My Account</a></td>\n" +
                    "                          </tr>\n" +
                    "                        </table>\n" +
                    "                      </td>\n" +
                    "                    </tr>\n" +
                    "                  </table>\n" +
                    "                </td>\n" +
                    "              </tr>\n" +
                    "              <!-- COPY -->\n" +
                    "              <tr>\n" +
                    "                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #666666; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 25px;\">\n" +
                    "                  <p style=\"margin: 0;\">If you have any questions, just reply to this email—we're always happy to help out.</p>\n" +
                    "                </td>\n" +
                    "              </tr>\n" +
                    "              <!-- COPY -->\n" +
                    "              <tr>\n" +
                    "                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 25px;\">\n" +
                    "                  <p style=\"margin: 0;\">Cheers,<br>TheThemeio Team</p>\n" +
                    "                </td>\n" +
                    "              </tr>\n" +
                    "            </table>\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            </td>\n" +
                    "            </tr>\n" +
                    "            </table>\n" +
                    "            <![endif]-->\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <!-- SUPPORT CALLOUT -->\n" +
                    "    <tr>\n" +
                    "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 30px 10px 0px 10px;\">\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
                    "            <tr>\n" +
                    "            <td align=\"center\" valign=\"top\" width=\"600\">\n" +
                    "            <![endif]-->\n" +
                    "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                    "                <!-- HEADLINE -->\n" +
                    "                <tr>\n" +
                    "                  <td bgcolor=\"#fafafa\" align=\"center\" style=\"padding: 30px 30px 30px 30px; border-radius: 4px 4px 4px 4px; color: #666666; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 25px;\">\n" +
                    "                    <h2 style=\"font-size: 16px; font-weight: 400; color: #111111; margin: 0;\">Need more help?</h2>\n" +
                    "                    <p style=\"margin: 0; font-size: 14px;\"><a href=\"http://thetheme.io\" target=\"_blank\" style=\"color: #33cabb;\">We&rsquo;re here, ready to talk</a></p>\n" +
                    "                  </td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            </td>\n" +
                    "            </tr>\n" +
                    "            </table>\n" +
                    "            <![endif]-->\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <!-- FOOTER -->\n" +
                    "    <tr>\n" +
                    "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
                    "            <tr>\n" +
                    "            <td align=\"center\" valign=\"top\" width=\"600\">\n" +
                    "            <![endif]-->\n" +
                    "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                    "              <!-- NAVIGATION -->\n" +
                    "              <tr>\n" +
                    "                <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 30px 30px 30px 30px; color: #aaaaaa; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 12px; font-weight: 400; line-height: 18px;\">\n" +
                    "                  <p style=\"margin: 0;\">\n" +
                    "                    <a href=\"http://thetheme.io\" target=\"_blank\" style=\"color: #999999; font-weight: 700;\">Dashboard</a> -\n" +
                    "                    <a href=\"http://thetheme.io\" target=\"_blank\" style=\"color: #999999; font-weight: 700;\">Billing</a> -\n" +
                    "                    <a href=\"http://thetheme.io\" target=\"_blank\" style=\"color: #999999; font-weight: 700;\">Help</a>\n" +
                    "                  </p>\n" +
                    "                </td>\n" +
                    "              </tr>\n" +
                    "              <!-- PERMISSION REMINDER -->\n" +
                    "              <tr>\n" +
                    "                <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 0px 30px 30px 30px; color: #aaaaaa; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 12px; font-weight: 400; line-height: 18px;\">\n" +
                    "                  <p style=\"margin: 0;\">You received this email because you just signed up for a new account. If it looks weird, <a href=\"http://thetheme.io\" target=\"_blank\" style=\"color: #999999; font-weight: 700;\">view it in your browser</a>.</p>\n" +
                    "                </td>\n" +
                    "              </tr>\n" +
                    "              <!-- UNSUBSCRIBE -->\n" +
                    "              <tr>\n" +
                    "                <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 0px 30px 30px 30px; color: #aaaaaa; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;\">\n" +
                    "                  <p style=\"margin: 0;\">If these emails get annoying, please feel free to <a href=\"http://thetheme.io\" target=\"_blank\" style=\"color: #999999; font-weight: 700;\">unsubscribe</a>.</p>\n" +
                    "                </td>\n" +
                    "              </tr>\n" +
                    "              <!-- ADDRESS -->\n" +
                    "              <tr>\n" +
                    "                <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 0px 30px 30px 30px; color: #aaaaaa; font-family: &apos;Lato&apos;, Helvetica, Arial, sans-serif; font-size: 12px; font-weight: 400; line-height: 18px;\">\n" +
                    "                  <p style=\"margin: 0;\">TheTheme - 1234 Main Street - Anywhere, MA - 56789</p>\n" +
                    "                </td>\n" +
                    "              </tr>\n" +
                    "            </table>\n" +
                    "            <!--[if (gte mso 9)|(IE)]>\n" +
                    "            </td>\n" +
                    "            </tr>\n" +
                    "            </table>\n" +
                    "            <![endif]-->\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";
            String templateContent = "<html><body><h1>Hello, {name}!</h1><p>This is an example email template.</p></body></html>";

            // Replace placeholders in the template with actual values
            String mergedContent = templateContent.replace("{name}", "John Doe");

            // Set the HTML content of the message
            message.setContent(t4, "text/html");
            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
