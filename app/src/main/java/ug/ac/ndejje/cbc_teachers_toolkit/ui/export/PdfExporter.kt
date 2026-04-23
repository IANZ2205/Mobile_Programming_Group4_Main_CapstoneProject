package ug.ac.ndejje.cbc_teachers_toolkit.ui.export

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import java.io.File

object PdfExporter {
    fun exportNotesSummary(context: Context, lines: List<String>): File {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint().apply { textSize = 12f }

        var y = 36f
        canvas.drawText("CBC Teaching Toolkit - Notes Export", 36f, y, paint)
        y += 24f
        lines.forEach { line ->
            if (y > 800f) return@forEach
            canvas.drawText(line.take(90), 36f, y, paint)
            y += 18f
        }
        document.finishPage(page)

        val file = File(context.cacheDir, "cbc_notes_export.pdf")
        file.outputStream().use { document.writeTo(it) }
        document.close()
        return file
    }
}
