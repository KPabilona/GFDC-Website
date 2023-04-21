package com.capstone.dentalclinic.demo.mail.email_template;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class AppointmentNotification {

    public String appointmentNotification(String firstName, String lastName, LocalDate localDate,
                                          String time, Integer queue) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                "    <title>Gillego-Flores Dental Clinic</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <!DOCTYPE html>\n" +
                "    <html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "      <head>\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "        \n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "        <style type=\"text/css\">\n" +
                "          #outlook a {\n" +
                "            padding: 0;\n" +
                "          }\n" +
                "      \n" +
                "          body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -webkit-text-size-adjust: 100%;\n" +
                "            -ms-text-size-adjust: 100%;\n" +
                "          }\n" +
                "      \n" +
                "          table,\n" +
                "          td {\n" +
                "            border-collapse: collapse;\n" +
                "            mso-table-lspace: 0pt;\n" +
                "            mso-table-rspace: 0pt;\n" +
                "          }\n" +
                "      \n" +
                "          img {\n" +
                "            border: 0;\n" +
                "            height: auto;\n" +
                "            line-height: 100%;\n" +
                "            outline: none;\n" +
                "            text-decoration: none;\n" +
                "            -ms-interpolation-mode: bicubic;\n" +
                "          }\n" +
                "      \n" +
                "          p {\n" +
                "            display: block;\n" +
                "            margin: 13px 0;\n" +
                "          }\n" +
                "        </style>\n" +
                "    \n" +
                "        <style type=\"text/css\">\n" +
                "          @media only screen and (min-width:480px) {\n" +
                "            .mj-column-per-100 {\n" +
                "              width: 100% !important;\n" +
                "              max-width: 100%;\n" +
                "            }\n" +
                "          }\n" +
                "        </style>\n" +
                "        <style type=\"text/css\">\n" +
                "          @media only screen and (max-width:480px) {\n" +
                "            table.mj-full-width-mobile {\n" +
                "              width: 100% !important;\n" +
                "            }\n" +
                "      \n" +
                "            td.mj-full-width-mobile {\n" +
                "              width: auto !important;\n" +
                "            }\n" +
                "          }\n" +
                "        </style>\n" +
                "        <style type=\"text/css\">\n" +
                "          a,\n" +
                "          span,\n" +
                "          td,\n" +
                "          th {\n" +
                "            -webkit-font-smoothing: antialiased !important;\n" +
                "            -moz-osx-font-smoothing: grayscale !important;\n" +
                "          }\n" +
                "        </style>\n" +
                "      </head>\n" +
                "      \n" +
                "      <body style=\"background-color:#ffffff;\">\n" +
                "        <div style=\"background-color:#ffffff;\">\n" +
                "         \n" +
                "          <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-bottom:0px;text-align:center;\">\n" +
                "                   \n" +
                "                    <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                          <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                            <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">\n" +
                "                              <h1 style=\"margin: 0; font-size: 24px; line-height: normal; font-weight: bold;\">APPOINTMENT CONFIRMED</h1>\n" +
                "                            </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "         \n" +
                "          <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-bottom:0px;text-align:center;\">\n" +
                "                    \n" +
                "                    <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                        <tbody><tr>\n" +
                "                          <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                            <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">\n" +
                "                              <p style=\"margin: 0;\">Hi "+ lastName + ", " + firstName +"</p>\n" +
                "                            </div>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                            <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">\n" +
                "                              <p style=\"margin: 0;\">We are pleased to inform you that your " +
                "schedule has been confirmed on " + localDate + " at "+ time + " at Gillego-Flores Dental Clinic.Your " +"reference " +
                "code for this is <strong>" + queue + "</strong> which will be used for validation purposes.</p>\n" +
                "                            </div>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody></table>\n" +
                "                    </div>\n" +
                "                  \n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "          \n" +
                "          <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
                "                   \n" +
                "                    <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                        <tbody><tr>\n" +
                "                          <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                            <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\">\n" +
                "                              <p style=\"margin: 0;\"><strong style=\"font-size: 16px; color: #999; font-weight: 400; " +
                "                               line-height: 18px\">Please take note of the following:</strong><br/><br>" +
                "- Arrive 15 minutes before your scheduled appointment.<br>" +
                "- Clients with cough or colds cannot undergo treatment and should secure a medical certificate.<br>" +
                "- Only one companion is allowed for minors and/or senior citizens.<br>" +
                "- No face mask, no entry.<br>" +
                "- Thermal scanning and disinfection will be done upon entry.<br>" +
                "- Sanitize or wash your hands before and after treatment.<br>" +
                "- Observe social distancing.<br>" +
                "- Present 1 Valid ID together with the unique code at the concierge for verification.<br></p>"+
                "<p style=\"padding-top: 5px;\">Rescheduling or cancellation should be done 3 hours before your " +
                "intended appointment.</p>"+
                "<p style=\"padding-top: 5px;\">Thank you!</p>"+
                "                            </div>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody></table>\n" +
                "                    </div>\n" +
                "                  \n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "          \n" +
                "          <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-top:0px;text-align:center;\">\n" +
                "                   \n" +
                "                    <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                        <tbody>\n" +"<tr>\n" +
                "                            <td align=\"left\" style=\"font-size:0px;padding:25px 25px;word-break:break-word;\">\n" +
                "                              <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;line-height:24px;text-align:left;color:#434245;\">NOTE: Please do not reply to this email. This email is a system-generated notification.</div>\n" +
                "                            </td>\n" +
                "                          </tr>"+
                "                        <tr>\n" +
                "                          <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                            <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:bold;line-height:24px;text-align:left;color:#434245;\">Team Gillego-Flores Dental Clinic</div>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                    \n" +
                "                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                              <tbody><tr>\n" +
                "                                <td style=\"padding:4px;\">\n" +
                "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-radius:3px;width:18px;\">\n" +
                "                                    <tbody><tr>\n" +
                "                                      <td style=\"font-size:0;height:18px;vertical-align:middle;width:18px;\">\n" +
                "                                        <a href=\"https://www.facebook.com/gfdc071520\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color: #2e58ff; text-decoration: none;\">\n" +
                "                                          <img alt=\"facebook-logo\" height=\"18\" src=\"https://codedmails.com/images/social/black/facebook-logo-transparent-black.png\" style=\"border-radius:3px;display:block;\" width=\"18\" />\n" +
                "                                        </a>\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                  </tbody></table>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </tbody>\n" +
                "                          </table>\n" +
                "                       \n" +
                "                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"float:none;display:inline-table;\">\n" +
                "                              <tbody><tr>\n" +
                "                                <td style=\"padding:4px;\">\n" +
                "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-radius:3px;width:18px;\">\n" +
                "                                    <tbody><tr>\n" +
                "                                      <td style=\"font-size:0;height:18px;vertical-align:middle;width:18px;\">\n" +
                "                                        <a href=\"https://www.instagram.com/gf_dental/\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color: #2e58ff; text-decoration: none;\">\n" +
                "                                          <img alt=\"instagram-logo\" height=\"18\" src=\"https://codedmails.com/images/social/black/instagram-logo-transparent-black.png\" style=\"border-radius:3px;display:block;\" width=\"18\" />\n" +
                "                                        </a>\n" +
                "                                      </td>\n" +
                "                                    </tr>\n" +
                "                                  </tbody></table>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </tbody></table>\n" +
                "                       \n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody></table>\n" +
                "                    </div>\n" +
                "                   \n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "          \n" +
                "          <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;padding-top:0;text-align:center;\">\n" +
                "                    \n" +
                "                    <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                        <tbody><tr>\n" +
                "                          <td style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                            <p style=\"border-top: dashed 1px lightgrey; font-size: 1px; margin: 0px auto; width: 100%;\">\n" +
                "                            </p>\n" +
                "                           \n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                            <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:14px;font-weight:400;line-height:24px;text-align:left;color:#999999;\">Have questions or need help? Email us at <a href=\"#\" style=\"color: #2e58ff; text-decoration: none;\">gillegoflores.dentalclinic@gmail.com</a></div>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                "                            <div style=\"font-family:Helvetica, Arial, sans-serif;font-size:18px;font-weight:400;line-height:24px;text-align:left;color:#434245;\"> 2nd Floor, Barangka Commercial Space A. Bonifacio Street, Marikina, Philippines.<br /> © 2023 Gillego-Flores Dental Clinic</div>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        \n" +
                "                      </tbody></table>\n" +
                "                    </div>\n" +
                "                  \n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "          \n" +
                "          <div style=\"margin:0px auto;max-width:600px;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\">\n" +
                "                    \n" +
                "                    <div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                "                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                "                        <tbody><tr>\n" +
                "                          <td style=\"font-size:0px;word-break:break-word;\">\n" +
                "                           \n" +
                "                            <div style=\"height:1px;\">   </div>\n" +
                "                            \n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody></table>\n" +
                "                    </div>\n" +
                "                   \n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </div>\n" +
                "          \n" +
                "        </div>\n" +
                "      </body></html>\n" +
                "</body>\n" +
                "</html>";
    }
}
