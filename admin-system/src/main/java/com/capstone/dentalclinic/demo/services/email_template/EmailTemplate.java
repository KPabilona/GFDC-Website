package com.capstone.dentalclinic.demo.services.email_template;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplate {

    public String adminValidation(String firstName,
                               String lastName,
                               String emailAddress,
                               String address,
                               String contactNumber,
                               String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" + 
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;" +
                "color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm Registration " +
                "Request" +
                " " + "</span" +
                ">\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi Admin " + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Someone Successfully Registered as an Admin, and you can verify it's credentials, the credentials are showed bellow: </p>" + "</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> FirstName: " + firstName + " </p>" +
                "</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> LastName: "+ lastName + " " +
                "</p>" + "</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Email " +
                "Address: "+ emailAddress + " </p>" + "</p><p style=\"Margin:0 0 20px 0;font-size:19px;" +
                "line-height:25px;color:#0b0c0c\">ContactNumber: "+ contactNumber + " </p>" + "</p><p " +
                "style=\"Margin" +
                ":0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Home Address: " + address + " </p>" +
                "<blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;" +
                "font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;" +
                "color:#0b0c0c\"> <a href=\"" + link + "\" target=\"_blank\">Activate Now</a> </p></blockquote>\n " +
                "Link will " +
                "expire in " +
                "30 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    
    public String notificationConfirmed(String firstName,
                                        String lastName) {
    return "<!DOCTYPE html><html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head>\n" +
            "    <title> Welcome to Coded Mails </title>\n" +
            "    <!--[if !mso]><!-- -->\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
            "    <!--<![endif]-->\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
            "    <style type=\"text/css\">\n" +
            "      #outlook a {\n" +
            "        padding: 0;\n" +
            "      }\n" +
            "  \n" +
            "      body {\n" +
            "        margin: 0;\n" +
            "        padding: 0;\n" +
            "        -webkit-text-size-adjust: 100%;\n" +
            "        -ms-text-size-adjust: 100%;\n" +
            "      }\n" +
            "  \n" +
            "      table,\n" +
            "      td {\n" +
            "        border-collapse: collapse;\n" +
            "        mso-table-lspace: 0pt;\n" +
            "        mso-table-rspace: 0pt;\n" +
            "      }\n" +
            "  \n" +
            "      img {\n" +
            "        border: 0;\n" +
            "        height: auto;\n" +
            "        line-height: 100%;\n" +
            "        outline: none;\n" +
            "        text-decoration: none;\n" +
            "        -ms-interpolation-mode: bicubic;\n" +
            "      }\n" +
            "  \n" +
            "      p {\n" +
            "        display: block;\n" +
            "        margin: 13px 0;\n" +
            "      }\n" +
            "    </style>\n" +
            "   \n" +
            "    <style type=\"text/css\">\n" +
            "      @media only screen and (min-width:480px) {\n" +
            "        .mj-column-per-100 {\n" +
            "          width: 100% !important;\n" +
            "          max-width: 100%;\n" +
            "        }\n" +
            "      }\n" +
            "    </style>\n" +
            "    <style type=\"text/css\">\n" +
            "      @media only screen and (max-width:480px) {\n" +
            "        table.mj-full-width-mobile {\n" +
            "          width: 100% !important;\n" +
            "        }\n" +
            "  \n" +
            "        td.mj-full-width-mobile {\n" +
            "          width: auto !important;\n" +
            "        }\n" +
            "      }\n" +
            "    </style>\n" +
            "    <style type=\"text/css\">\n" +
            "      a,\n" +
            "      span,\n" +
            "      td,\n" +
            "      th {\n" +
            "        -webkit-font-smoothing: antialiased !important;\n" +
            "        -moz-osx-font-smoothing: grayscale !important;\n" +
            "      }\n" +
            "    </style>\n" +
            "  </head>\n" +
            "  \n" +
            "  <body style=\"background-color:#ffffff;\">\n" +
            "    <div style=\"display:none;font-size:1px;color:#ffffff;line-height:1px;max-height:0px;max-width:0px;opacity:0;overflow:hidden;\"> Preview - Welcome to Coded Mails </div>\n" +
            "    <div style=\"background-color:#ffffff;\">\n" +
            "      \n" +
            "      <div style=\"margin:0px auto;max-width:600px;\">\n" +
            "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
            "          <tbody>\n" +
            "            <tr>\n" +
            "              <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-bottom:0px;text-align:center;\">\n" +
            "               \n" +
            "                <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
            "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
            "                    <tbody><tr>\n" +
            "                      <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
            "                          <tbody>\n" +
            "                            <tr>\n" +
            "                              <td style=\"width:70px;\">\n" +
        //     "                                <img alt=\"GFDC-logo\" height=\"auto\" src=\"../assets/logo.jpg\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:14px;\" width=\"50\" />\n" +
            "                              </td>\n" +
            "                            </tr>\n" +
            "                          </tbody>\n" +
            "                        </table>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                        <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">\n" +
            "                          <h1 style=\"margin: 0; font-size: 24px; line-height: normal; font-weight: bold;\"> Welcome to <br />Gillego-Flores Dental Clinic!</h1>\n" +
            "                        </div>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                  </tbody></table>\n" +
            "                </div>\n" +
            "          \n" +
            "              </td>\n" +
            "            </tr>\n" +
            "          </tbody>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "    \n" +
            "      <div style=\"margin:0px auto;max-width:600px;\">\n" +
            "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
            "          <tbody>\n" +
            "            <tr>\n" +
            "              <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
            "               \n" +
            "                <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
            "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
            "                    <tbody>\n" +
            "                        <tr>\n" +
            "                            <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                                <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;" +
            "font-weight:400;line-height:24px;text-align:left;color:#434245;\">Hi There, " + firstName + " " + lastName + "</div>\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "                    <tr>\n" +
            "                        <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                            <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">We have approved your request to be one of our staff member and we are really excited to welcome you to our team.</div>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td align=\"left\" vertical-align=\"middle\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">\n" +
            "                        </table>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                        <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;" +
            "font-weight:400;line-height:24px;text-align:left;color:#434245;\">If you need any help, don’t hesitate " +
            "to reach out to us at <br><a href=\"https://mail.google.com/mail/u/0/#inbox?compose=VpCqJRzRljgXNJwWjfXgdhjjtkSqVpfcPdNTfDrbnhNCqTqNcLZrZXHWnvWbplMzDKtvzKB\" style=\"color: #2e58ff; text-decoration: none;" + "\">gillegoflores.dentalclinic@gmail.com</a>!</div>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                        <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:bold;line-height:24px;text-align:left;color:#434245;\">Team Gillego-Flores Dental Clinic</div>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                        <!--[if mso | IE]>\n" +
            "        <table\n" +
            "           align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
            "        >\n" +
            "          <tr>\n" +
            "                <td>\n" +
            "              <![endif]-->\n" +
            "                        <!-- <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
            "                          <tbody><tr>\n" +
            "                            <td style=\"padding:4px;\">\n" +
            "                              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-radius:3px;width:18px;\">\n" +
            "                                <tbody><tr>\n" +
            "                                  <td style=\"font-size:0;height:18px;vertical-align:middle;width:18px;\">\n" +
            "                                    <a href=\"#\" target=\"_blank\" style=\"color: #2e58ff; text-decoration: none;\">\n" +
            "                                      <img alt=\"facebook-logo\" height=\"18\" src=\"https://codedmails.com/images/social/black/facebook-logo-transparent-black.png\" style=\"border-radius:3px;display:block;\" width=\"18\" />\n" +
            "                                    </a>\n" +
            "                                  </td>\n" +
            "                                </tr>\n" +
            "                              </tbody></table>\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                        </tbody></table> -->\n" +
            "                        <!--[if mso | IE]>\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "          </table>\n" +
            "        <![endif]-->\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                  </tbody></table>\n" +
            "                </div>\n" +
            "              \n" +
            "              </td>\n" +
            "            </tr>\n" +
            "          </tbody>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "    \n" +
            "      <div style=\"margin:0px auto;max-width:600px;\">\n" +
            "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
            "          <tbody>\n" +
            "            <tr>\n" +
            "              <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-top:0;text-align:center;\">\n" +
            "                <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
            "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
            "                    <tbody><tr>\n" +
            "                      <td style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                        <p style=\"border-top: dashed 1px lightgrey; font-size: 1px; margin: 0px auto; width: 100%;\">\n" +
            "                        </p>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                        <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:14px;" +
            "font-weight:400;line-height:24px;text-align:left;color:#999999;\">Have questions or need help? Email us " +
            "at <a href=\"https://mail.google.com/mail/u/0/#inbox?compose=VpCqJRzRljgXNJwWjfXgdhjjtkSqVpfcPdNTfDrbnhNCqTqNcLZrZXHWnvWbplMzDKtvzKB\" style=\"color: #2e58ff; text-decoration: none;\">gillegoflores.dentalclinic@gmail.com " +
            "</a></div>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
            "                        <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">Barangka Barangay Hall<br /> © 2022 Gillego-Flores Dental Clinic.</div>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                  </tbody></table>\n" +
            "                </div>\n" +
            "              \n" +
            "              </td>\n" +
            "            </tr>\n" +
            "          </tbody>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "    \n" +
            "      <div style=\"margin:0px auto;max-width:600px;\">\n" +
            "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
            "          <tbody>\n" +
            "            <tr>\n" +
            "              <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
            "                \n" +
            "                <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
            "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
            "                    <tbody><tr>\n" +
            "                      <td style=\"font-size:0px;word-break:break-word;\">\n" +
            "              </td>\n" +
            "            </tr>\n" +
            "          </tbody>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "      \n" +
            "    </div>\n" +
            "  \n" +
            "  \n" +
            "  </body></html>";   

    }
}
