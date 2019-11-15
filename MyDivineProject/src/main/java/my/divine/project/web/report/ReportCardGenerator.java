package my.divine.project.web.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import my.divine.project.model.entity.Course;
import my.divine.project.model.entity.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


/**
 * Report card Generator in pdf format is
 * a source of information for the user for the course.
 */
public class ReportCardGenerator {


        private static final Font BIG_FONT = new Font(Font.FontFamily.TIMES_ROMAN,
                18, Font.BOLD);
        private static final Font SMALL_FONT = new Font(Font.FontFamily.TIMES_ROMAN,
                10, Font.ITALIC | Font.BOLD);
        private static final int TITLE_TOP_MERGING = 10;

        private static final int PARAGRAPH_BOTTOM_MERGING = 3;

        private static final int PARAGRAPH_TOP_MERGING = 15;

        private static final int BOTTOM_MARGIN = 7;

        private ReportCardGenerator() {

        }

        public static void createTicket(String path, User user, Course course, String assessment)
                throws IOException, DocumentException {
            File file = new File(path);
            file.mkdirs();
            // Document creation
            Document ticket = new Document(PageSize.A5);


            PdfWriter.getInstance(ticket,
                    new FileOutputStream(String.format("%s%s.pdf", path, user.getLogin())));

            ticket.open();
            // Title
            Paragraph title = getTitle();
            ticket.add(title);

            // Course paragraph
            ticket.add(getParagraph("Information about course:"));

            // Table of course
            ticket.add(getTableOfCourse(course));

            // User paragraph
            ticket.add(getParagraph("Information about student:"));

            // Table of user
            ticket.add(getTableOfUser(user, assessment));





            // Current date paragraph
            ticket.add(getCurrentDate());

            // Image, logo
        /*Image stamp = getStampImg(file);
        ticket.add(stamp);*/
            ticket.close();
        }

        private static Paragraph getCurrentDate() {
            Paragraph paragraph = new Paragraph(2);
            paragraph.setSpacingBefore(PARAGRAPH_TOP_MERGING);
            paragraph.setFont(SMALL_FONT);
            paragraph.setSpacingAfter(PARAGRAPH_BOTTOM_MERGING);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.add(new Chunk("Current date: "));
            paragraph.add(new Chunk(new Date().toString()));
            return paragraph;
        }

        private static PdfPTable getTableOfUser(User user, String assessment) {
            PdfPTable tableOfUserInfo = new PdfPTable(2);

            tableOfUserInfo.addCell("User login:");
            tableOfUserInfo.addCell(user.getLogin());

            tableOfUserInfo.addCell("User full name:");
            tableOfUserInfo.addCell(user.getFullName());

            tableOfUserInfo.addCell("Assessment:");
            tableOfUserInfo.addCell(assessment);



            return tableOfUserInfo;
        }

        private static Paragraph getParagraph(String chunk) {
            Paragraph paragraph = new Paragraph();
            paragraph.setFont(SMALL_FONT);
            paragraph.setSpacingAfter(PARAGRAPH_BOTTOM_MERGING);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(new Chunk(chunk));
            return paragraph;
        }

        private static Image getStampImg(File file) throws BadElementException, IOException {
            String imageUrl = "stamp.png";
            Image stamp = Image.getInstance(imageUrl);
            stamp.setAlignment(Element.ALIGN_RIGHT);
            return stamp;
        }

        private static PdfPTable getTableOfCourse(Course course) {
            PdfPTable tableOfCourseInfo = new PdfPTable(2);

            tableOfCourseInfo.addCell("Course topic:");
            tableOfCourseInfo.addCell(course.getTopic().toString());


            tableOfCourseInfo.addCell("Course name:");
            tableOfCourseInfo.addCell(course.getName());

            tableOfCourseInfo.addCell("Course start date:");
            tableOfCourseInfo.addCell(course.getStartDate().toString());

            tableOfCourseInfo.addCell("Course end date:");
            tableOfCourseInfo.addCell(course.getEndDate().toString());
            return tableOfCourseInfo;
        }

        private static Paragraph getTitle() {
            Paragraph title = new Paragraph("REPORT CARD", BIG_FONT);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(TITLE_TOP_MERGING);
            title.setSpacingAfter(BOTTOM_MARGIN);
            return title;
        }
}
