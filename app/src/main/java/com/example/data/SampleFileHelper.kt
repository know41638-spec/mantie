package com.example.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import java.io.File
import java.io.FileOutputStream

object SampleFileHelper {

    fun ensureSampleFilesAndGetSeedData(context: Context): List<StudyMaterial> {
        val studyDir = File(context.filesDir, "study_files")
        if (!studyDir.exists()) {
            studyDir.mkdirs()
        }

        // 1. Create a real sample multi-page PDF document
        val samplePdfFile = File(studyDir, "class11_pcm_sample_notes.pdf")
        if (!samplePdfFile.exists()) {
            try {
                val pdfDoc = PdfDocument()

                // Page 1: Title & Physics Key Formulas
                val pageInfo1 = PdfDocument.PageInfo.Builder(595, 842, 1).create()
                val page1 = pdfDoc.startPage(pageInfo1)
                val canvas1 = page1.canvas
                val paint = Paint()

                // Background
                paint.color = Color.rgb(248, 250, 252)
                canvas1.drawRect(0f, 0f, 595f, 842f, paint)

                // Header banner
                paint.color = Color.rgb(15, 76, 129)
                canvas1.drawRect(0f, 0f, 595f, 80f, paint)

                paint.color = Color.WHITE
                paint.textSize = 22f
                paint.isFakeBoldText = true
                canvas1.drawText("Naitik Jain – Class 11 PCM", 36f, 48f, paint)

                paint.textSize = 12f
                paint.isFakeBoldText = false
                canvas1.drawText("Comprehensive Quick Reference & Formula Sheet [Sample Document]", 36f, 68f, paint)

                // Body content
                paint.color = Color.rgb(15, 23, 42)
                paint.textSize = 16f
                paint.isFakeBoldText = true
                canvas1.drawText("1. Physics Kinematics & Mechanics Essentials", 36f, 120f, paint)

                paint.textSize = 12f
                paint.isFakeBoldText = false
                val physicsLines = listOf(
                    "• Equations of Motion (Uniform Acceleration):",
                    "    v = u + at",
                    "    s = ut + ½at²",
                    "    v² = u² + 2as",
                    "    s_nth = u + ½ a(2n - 1)",
                    "",
                    "• Projectile Motion (Horizontal range & Maximum height):",
                    "    Time of flight: T = (2 u sinθ) / g",
                    "    Maximum height: H = (u² sin²θ) / (2g)",
                    "    Horizontal range: R = (u² sin 2θ) / g",
                    "",
                    "• Newton's Laws and Friction:",
                    "    F = dp/dt = ma",
                    "    Static friction limit: f_s(max) = μ_s N",
                    "    Kinetic friction: f_k = μ_k N",
                    "",
                    "• Work-Energy Theorem:",
                    "    W_net = ΔK = ½ m v² - ½ m u²",
                    "    Power P = F · v = dW/dt"
                )
                var y = 145f
                for (line in physicsLines) {
                    canvas1.drawText(line, 40f, y, paint)
                    y += 18f
                }

                // Page footer
                paint.color = Color.GRAY
                paint.textSize = 10f
                canvas1.drawText("Page 1 of 2 • Class 11 PCM Study Material • Naitik Jain", 36f, 810f, paint)
                pdfDoc.finishPage(page1)

                // Page 2: Chemistry & Maths Essentials
                val pageInfo2 = PdfDocument.PageInfo.Builder(595, 842, 2).create()
                val page2 = pdfDoc.startPage(pageInfo2)
                val canvas2 = page2.canvas

                paint.color = Color.rgb(248, 250, 252)
                canvas2.drawRect(0f, 0f, 595f, 842f, paint)

                paint.color = Color.rgb(2, 136, 209)
                canvas2.drawRect(0f, 0f, 595f, 80f, paint)

                paint.color = Color.WHITE
                paint.textSize = 22f
                paint.isFakeBoldText = true
                canvas2.drawText("Chemistry & Mathematics Review", 36f, 48f, paint)
                paint.textSize = 12f
                paint.isFakeBoldText = false
                canvas2.drawText("Core Principles & Standard Formulas for Class 11", 36f, 68f, paint)

                paint.color = Color.rgb(15, 23, 42)
                paint.textSize = 16f
                paint.isFakeBoldText = true
                canvas2.drawText("2. Chemistry: Mole Concept & Thermodynamics", 36f, 120f, paint)

                val chemLines = listOf(
                    "• Moles = Mass in grams / Molar mass",
                    "• Molarity M = (Moles of solute) / (Liters of solution)",
                    "• Ideal Gas Equation: P V = n R T",
                    "• First Law of Thermodynamics: ΔU = q + w",
                    "• Enthalpy: ΔH = ΔU + Δn_g R T",
                    "• Gibbs Free Energy: ΔG = ΔH - T ΔS (Spontaneous when ΔG < 0)"
                )
                y = 145f
                paint.textSize = 12f
                paint.isFakeBoldText = false
                for (line in chemLines) {
                    canvas2.drawText(line, 40f, y, paint)
                    y += 18f
                }

                y += 20f
                paint.textSize = 16f
                paint.isFakeBoldText = true
                canvas2.drawText("3. Mathematics: Trigonometry & Calculus", 36f, y, paint)

                y += 25f
                val mathLines = listOf(
                    "• sin(A ± B) = sinA cosB ± cosA sinB",
                    "• cos(A ± B) = cosA cosB ∓ sinA sinB",
                    "• cos 2θ = cos²θ - sin²θ = 2cos²θ - 1 = 1 - 2sin²θ",
                    "• d/dx (xⁿ) = n xⁿ⁻¹",
                    "• d/dx (sin x) = cos x ;  d/dx (cos x) = -sin x",
                    "• Product Rule: d/dx (uv) = u'v + uv'"
                )
                paint.textSize = 12f
                paint.isFakeBoldText = false
                for (line in mathLines) {
                    canvas2.drawText(line, 40f, y, paint)
                    y += 18f
                }

                paint.color = Color.GRAY
                paint.textSize = 10f
                canvas2.drawText("Page 2 of 2 • Class 11 PCM Study Material • Naitik Jain", 36f, 810f, paint)
                pdfDoc.finishPage(page2)

                FileOutputStream(samplePdfFile).use { out ->
                    pdfDoc.writeTo(out)
                }
                pdfDoc.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // 2. Create a sample science diagram image
        val sampleImgFile = File(studyDir, "projectile_motion_diagram.jpg")
        if (!sampleImgFile.exists()) {
            try {
                val bitmap = Bitmap.createBitmap(800, 500, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                val paint = Paint(Paint.ANTI_ALIAS_FLAG)

                // Background
                paint.color = Color.rgb(255, 255, 255)
                canvas.drawRect(0f, 0f, 800f, 500f, paint)

                // Title
                paint.color = Color.rgb(15, 76, 129)
                paint.textSize = 24f
                paint.isFakeBoldText = true
                canvas.drawText("Physics Class 11 – Projectile Trajectory Diagram", 40f, 50f, paint)

                paint.color = Color.rgb(100, 116, 139)
                paint.textSize = 14f
                paint.isFakeBoldText = false
                canvas.drawText("Parabolic trajectory showing Launch Velocity (u), Angle (θ), Max Height (H), and Range (R)", 40f, 75f, paint)

                // Axes
                paint.color = Color.DKGRAY
                paint.strokeWidth = 3f
                // Y-axis
                canvas.drawLine(80f, 420f, 80f, 120f, paint)
                // X-axis
                canvas.drawLine(80f, 420f, 740f, 420f, paint)

                // Ground line & labels
                paint.textSize = 16f
                paint.isFakeBoldText = true
                paint.color = Color.rgb(15, 23, 42)
                canvas.drawText("Y (Height)", 50f, 110f, paint)
                canvas.drawText("X (Range)", 700f, 445f, paint)

                // Draw parabolic trajectory curve
                paint.color = Color.rgb(30, 136, 229)
                paint.strokeWidth = 5f
                paint.style = Paint.Style.STROKE
                val path = android.graphics.Path()
                path.moveTo(80f, 420f)
                path.quadTo(400f, 80f, 720f, 420f)
                canvas.drawPath(path, paint)

                // Max Height dashed indicator
                paint.style = Paint.Style.STROKE
                paint.color = Color.rgb(230, 81, 0)
                paint.strokeWidth = 2f
                paint.pathEffect = android.graphics.DashPathEffect(floatArrayOf(10f, 10f), 0f)
                canvas.drawLine(400f, 420f, 400f, 250f, paint)
                paint.pathEffect = null

                // Labels
                paint.style = Paint.Style.FILL
                paint.color = Color.rgb(230, 81, 0)
                paint.textSize = 14f
                paint.isFakeBoldText = true
                canvas.drawText("H_max = (u² sin²θ)/(2g)", 410f, 240f, paint)

                paint.color = Color.rgb(15, 76, 129)
                canvas.drawText("Total Range R = (u² sin 2θ)/g", 300f, 450f, paint)
                canvas.drawText("Launch Angle θ", 100f, 400f, paint)

                FileOutputStream(sampleImgFile).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Return initial sample items
        return listOf(
            // Sample PDF Item
            StudyMaterial(
                subjectId = "PHYSICS",
                chapterNumber = 1,
                chapterTitle = "Units and Measurements",
                category = "Reference Material",
                materialType = "PDF",
                title = "Class 11 PCM Formula Sheet & Summary (PDF)",
                description = "2-page quick formula reference for Physics, Chemistry, and Maths.",
                content = "Multi-page reference document for quick revision before exams.",
                filePath = samplePdfFile.absolutePath,
                fileName = "class11_pcm_sample_notes.pdf",
                fileSize = samplePdfFile.length(),
                isSample = true
            ),
            // Sample Diagram Image
            StudyMaterial(
                subjectId = "PHYSICS",
                chapterNumber = 3,
                chapterTitle = "Motion in a Plane",
                category = "Reference Material",
                materialType = "IMAGE",
                title = "Projectile Motion Trajectory Diagram",
                description = "Handy diagram displaying maximum height, flight time, and range equations.",
                content = "High resolution diagram showing parabolic projectile flight path and vector components.",
                filePath = sampleImgFile.absolutePath,
                fileName = "projectile_motion_diagram.jpg",
                fileSize = sampleImgFile.length(),
                isSample = true
            ),
            // Notes for Physics Chapter 1
            StudyMaterial(
                subjectId = "PHYSICS",
                chapterNumber = 1,
                chapterTitle = "Units and Measurements",
                category = "School Notes",
                materialType = "NOTE",
                title = "Dimensional Analysis & Significant Figures",
                description = "Core rules for checking equation correctness and calculating percentage errors.",
                content = """
                    # Units & Measurements – Key Study Notes
                    
                    ### 1. Fundamental Quantities (SI System)
                    • Length (meter, m)
                    • Mass (kilogram, kg)
                    • Time (second, s)
                    • Electric Current (Ampere, A)
                    • Thermodynamic Temperature (Kelvin, K)
                    • Amount of Substance (mole, mol)
                    • Luminous Intensity (candela, cd)
                    
                    ### 2. Applications of Dimensional Analysis
                    1. Converting one system of units to another: n₁u₁ = n₂u₂
                    2. Checking the dimensional correctness of physical equations (Principle of Homogeneity: [LHS] = [RHS]).
                    3. Deducing relations among physical quantities.
                    
                    ### 3. Error Analysis
                    • Absolute Error: Δaᵢ = |a_mean - aᵢ|
                    • Mean Absolute Error: Δa_mean = (∑ Δaᵢ) / n
                    • Relative Error = Δa_mean / a_mean
                    • Percentage Error = (Relative Error) × 100%
                    
                    ### 4. Significant Figures Rules
                    • All non-zero digits are significant.
                    • All zeros between non-zero digits are significant.
                    • Leading zeros are never significant (e.g. 0.0025 has 2 sig figs).
                    • Trailing zeros after a decimal point are significant.
                """.trimIndent(),
                isSample = true
            ),
            // Notes for Physics Chapter 2
            StudyMaterial(
                subjectId = "PHYSICS",
                chapterNumber = 2,
                chapterTitle = "Motion in a Straight Line",
                category = "School Notes",
                materialType = "NOTE",
                title = "1D Kinematics Derivations & Graph Interpretation",
                description = "Calculus derivations of v=u+at, s=ut+½at², v²=u²+2as and v-t graph slopes.",
                content = """
                    # Motion in a Straight Line – Revision Notes
                    
                    ### 1. Velocity and Acceleration Definitions
                    • Instantaneous Velocity: v = dx/dt
                    • Instantaneous Acceleration: a = dv/dt = v (dv/dx)
                    
                    ### 2. Graphs
                    • Slope of position-time (x-t) graph = Velocity
                    • Slope of velocity-time (v-t) graph = Acceleration
                    • Area under velocity-time (v-t) graph = Displacement
                    • Area under acceleration-time (a-t) graph = Change in Velocity
                    
                    ### 3. Motion Under Gravity (g = 9.8 m/s²)
                    • Ball thrown vertically upward with initial speed u:
                      - Time to reach highest point: t = u / g
                      - Maximum height attained: H = u² / (2g)
                      - Total time of flight: T = 2u / g
                      - Striking velocity upon landing: v = u (downward)
                """.trimIndent(),
                isSample = true
            ),
            // Notes for Chemistry Chapter 1
            StudyMaterial(
                subjectId = "CHEMISTRY",
                chapterNumber = 1,
                chapterTitle = "Some Basic Concepts of Chemistry",
                category = "School Notes",
                materialType = "NOTE",
                title = "Mole Concept, Stoichiometry & Concentration Terms",
                description = "Molarity, Molality, Mole Fraction definitions and limiting reactant calculations.",
                content = """
                    # Some Basic Concepts of Chemistry
                    
                    ### 1. The Mole Concept
                    • 1 mole of any substance = 6.022 × 10²³ particles (Avogadro's constant, N_A).
                    • 1 mole of an ideal gas at STP (273.15 K, 1 bar) occupies 22.7 Liters (or 22.4 L at 1 atm).
                    • Moles (n) = Mass (w) / Molar mass (M) = Number of particles / N_A
                    
                    ### 2. Concentration of Solutions
                    • **Molarity (M)** = (Moles of solute) / (Volume of solution in Liters) [Temp dependent]
                    • **Molality (m)** = (Moles of solute) / (Mass of solvent in kg) [Temp independent]
                    • **Mole Fraction (X_A)** = n_A / (n_A + n_B) [Dimensionless]
                    • **Mass Percentage (w/w)** = (Mass of component / Total mass of solution) × 100%
                    
                    ### 3. Limiting Reagent
                    The reactant that is completely consumed first in a chemical reaction is called the limiting reagent. It limits the amount of product formed.
                """.trimIndent(),
                isSample = true
            ),
            // Notes for Chemistry Chapter 4
            StudyMaterial(
                subjectId = "CHEMISTRY",
                chapterNumber = 4,
                chapterTitle = "Chemical Bonding & Molecular Structure",
                category = "School Notes",
                materialType = "NOTE",
                title = "VSEPR Geometry & Hybridization Master Table",
                description = "Molecular shapes (Linear, Trigonal Planar, Tetrahedral, Trigonal Bipyramidal, Octahedral).",
                content = """
                    # Chemical Bonding & Molecular Structure
                    
                    ### 1. VSEPR Theory (Predicting Shapes)
                    Repulsion order: Lone Pair - Lone Pair > Lone Pair - Bond Pair > Bond Pair - Bond Pair
                    
                    • **Steric Number 2 (sp)**:
                      - 2 bond pairs, 0 lone pairs: Linear (180°), e.g. BeCl₂, CO₂
                    • **Steric Number 3 (sp²)**:
                      - 3 bond pairs, 0 lone pairs: Trigonal planar (120°), e.g. BF₃
                      - 2 bond pairs, 1 lone pair: Bent / V-shape (<120°), e.g. SO₂
                    • **Steric Number 4 (sp³)**:
                      - 4 bond pairs, 0 lone pairs: Tetrahedral (109.5°), e.g. CH₄
                      - 3 bond pairs, 1 lone pair: Trigonal Pyramidal (107°), e.g. NH₃
                      - 2 bond pairs, 2 lone pairs: Bent / V-shape (104.5°), e.g. H₂O
                    • **Steric Number 5 (sp³d)**:
                      - 5 bond pairs, 0 lone pairs: Trigonal Bipyramidal, e.g. PCl₅
                    • **Steric Number 6 (sp³d²)**:
                      - 6 bond pairs, 0 lone pairs: Octahedral (90°), e.g. SF₆
                    
                    ### 2. Molecular Orbital (MO) Bond Order
                    • Bond Order = ½ (N_b - N_a)
                    • If Bond Order > 0, molecule is stable. If Bond Order = 0, molecule cannot exist.
                """.trimIndent(),
                isSample = true
            ),
            // Notes for Maths Chapter 3
            StudyMaterial(
                subjectId = "MATHS",
                chapterNumber = 3,
                chapterTitle = "Trigonometric Functions",
                category = "School Notes",
                materialType = "NOTE",
                title = "Trigonometric Identities & Transformation Formulas",
                description = "Compound angle identities, double & triple angle formulas, and sum-to-product rules.",
                content = """
                    # Trigonometric Functions – Formula Sheet
                    
                    ### 1. Compound Angle Formulas
                    • sin(x + y) = sin x cos y + cos x sin y
                    • sin(x - y) = sin x cos y - cos x sin y
                    • cos(x + y) = cos x cos y - sin x sin y
                    • cos(x - y) = cos x cos y + sin x sin y
                    • tan(x + y) = (tan x + tan y) / (1 - tan x tan y)
                    • tan(x - y) = (tan x - tan y) / (1 + tan x tan y)
                    
                    ### 2. Double & Half Angle Formulas
                    • sin 2x = 2 sin x cos x = (2 tan x) / (1 + tan²x)
                    • cos 2x = cos²x - sin²x = 2cos²x - 1 = 1 - 2sin²x = (1 - tan²x) / (1 + tan²x)
                    • tan 2x = (2 tan x) / (1 - tan²x)
                    
                    ### 3. Transformation of Products into Sum/Difference
                    • 2 sin x cos y = sin(x + y) + sin(x - y)
                    • 2 cos x sin y = sin(x + y) - sin(x - y)
                    • 2 cos x cos y = cos(x + y) + cos(x - y)
                    • 2 sin x sin y = cos(x - y) - cos(x + y)
                """.trimIndent(),
                isSample = true
            ),
            // Notes for Maths Chapter 12
            StudyMaterial(
                subjectId = "MATHS",
                chapterNumber = 12,
                chapterTitle = "Limits and Derivatives",
                category = "School Notes",
                materialType = "NOTE",
                title = "Limits Theorems & First Principle Derivatives",
                description = "Standard trigonometric limits, algebraic evaluations, and differentiation rules.",
                content = """
                    # Limits and Derivatives – Study Notes
                    
                    ### 1. Fundamental Limit Theorems
                    • lim(x→0) [sin x / x] = 1 (x in radians)
                    • lim(x→0) [tan x / x] = 1
                    • lim(x→0) [(1 - cos x) / x] = 0
                    • lim(x→a) [(xⁿ - aⁿ) / (x - a)] = n aⁿ⁻¹
                    • lim(x→0) [(eˣ - 1) / x] = 1
                    • lim(x→0) [ln(1 + x) / x] = 1
                    
                    ### 2. First Principle of Derivative
                    f'(x) = lim(h→0) [f(x + h) - f(x)] / h
                    
                    ### 3. Key Derivatives
                    • d/dx (xⁿ) = n xⁿ⁻¹
                    • d/dx (sin x) = cos x
                    • d/dx (cos x) = -sin x
                    • d/dx (tan x) = sec²x
                    • d/dx (eˣ) = eˣ
                    • Product Rule: (u v)' = u' v + u v'
                    • Quotient Rule: (u / v)' = (u' v - u v') / v²
                """.trimIndent(),
                isSample = true
            ),
            // Other Material: School Notes
            StudyMaterial(
                subjectId = "OTHER",
                chapterNumber = 0,
                chapterTitle = "School Notes",
                category = "School Notes",
                materialType = "NOTE",
                title = "Class 11 Term Blueprint & Practical Exam Guidelines",
                description = "Syllabus breakdown for unit tests, half-yearly and final term exams.",
                content = """
                    # School Academic Blueprint – Class 11 PCM
                    
                    ### Physics Practicals:
                    • Vernier Caliper (Measurement of diameter of cylinder/sphere)
                    • Screw Gauge (Thickness of sheet/wire)
                    • Spherometer (Radius of curvature of spherical surface)
                    • Simple Pendulum (L-T² graph and determination of 'g')
                    • Parallelogram Law of Forces
                    
                    ### Chemistry Practicals:
                    • Volumetric Analysis: Titration of Oxalic acid against standard NaOH / KMnO₄
                    • Salt Analysis (Qualitative identification of cation and anion)
                    • Preparation of standard solution of oxalic acid
                    
                    ### Maths Activity Journal:
                    • Verification of (A ∪ B)' = A' ∩ B' using Venn diagrams
                    • Geometrical demonstration of cos(x+y) formula
                """.trimIndent(),
                isSample = true
            ),
            // Other Material: Question Papers
            StudyMaterial(
                subjectId = "OTHER",
                chapterNumber = 0,
                chapterTitle = "Question Papers",
                category = "Question Papers",
                materialType = "NOTE",
                title = "Class 11 PCM Practice Test & Question Bank",
                description = "High-yield 1-mark, 2-mark, 3-mark, and 5-mark conceptual problems.",
                content = """
                    # Class 11 PCM Model Practice Paper
                    
                    ### Section A: Physics (30 Marks)
                    1. A projectile is fired at 30° to horizontal with 40 m/s. Find time of flight and range. (3 marks)
                    2. State and prove the Work-Energy Theorem for a variable force. (5 marks)
                    3. Derive terminal velocity using Stokes' Law. (3 marks)
                    
                    ### Section B: Chemistry (30 Marks)
                    1. Calculate the number of moles and molecules in 44.8 L of CO₂ at STP. (2 marks)
                    2. Explain hybridization and geometry of PCl₅ and SF₆. (3 marks)
                    3. State Le Chatelier's principle and apply to Haber process for Ammonia. (5 marks)
                    
                    ### Section C: Mathematics (40 Marks)
                    1. Prove: cos 4x = 1 - 8 sin²x cos²x. (4 marks)
                    2. Find the 7th term in the expansion of (x/3 - 2/y)¹⁰. (3 marks)
                    3. Find derivative of f(x) = sin x from first principle. (5 marks)
                """.trimIndent(),
                isSample = true
            ),
            // Other Material: Reference Material
            StudyMaterial(
                subjectId = "OTHER",
                chapterNumber = 0,
                chapterTitle = "Reference Material",
                category = "Reference Material",
                materialType = "NOTE",
                title = "Class 11 PCM Recommended Textbooks & Reference Index",
                description = "NCERT, HC Verma, Pradeep, RD Sharma, and Exemplar guidance.",
                content = """
                    # Recommended Study Resources for Class 11 PCM
                    
                    • **Physics**:
                      1. NCERT Physics Part 1 & Part 2 (Mandatory)
                      2. Concepts of Physics by Dr. H.C. Verma (Vol 1)
                      3. NCERT Exemplar Physics Class 11
                    
                    • **Chemistry**:
                      1. NCERT Chemistry Part 1 & Part 2
                      2. Modern's ABC of Chemistry / Pradeep
                      3. NCERT Exemplar Chemistry Class 11
                    
                    • **Mathematics**:
                      1. NCERT Mathematics Class 11
                      2. R.D. Sharma Mathematics Class 11
                      3. NCERT Exemplar Problems in Mathematics
                """.trimIndent(),
                isSample = true
            ),
            // Other Material: Important Resources
            StudyMaterial(
                subjectId = "OTHER",
                chapterNumber = 0,
                chapterTitle = "Important Resources",
                category = "Important Resources",
                materialType = "RESOURCE",
                title = "Video Lectures & Digital Learning Portals",
                description = "Curated list of verified academic playlists and free reference channels.",
                content = """
                    # Free Online Video & Study Portals
                    
                    • **NCERT Official e-Books**:
                      Free PDF chapters available at ncert.nic.in/textbook.php
                      
                    • **DIKSHA Portal (Govt. of India)**:
                      Interactive video modules and chapter-wise quiz questions
                      
                    • **SWAYAM / NPTEL (IIT JEE Foundation)**:
                      In-depth concept lectures by IIT professors for Class 11 & 12
                      
                    • **PhET Interactive Simulations (Colorado University)**:
                      Hands-on physics and chemistry animations (Projectile motion, gas laws, balancing equations)
                """.trimIndent(),
                isSample = true
            ),
            // Other Material: Other
            StudyMaterial(
                subjectId = "OTHER",
                chapterNumber = 0,
                chapterTitle = "Other",
                category = "Other",
                materialType = "NOTE",
                title = "Class 11 Daily PCM Time-Management Schedule",
                description = "Balanced 3-hour evening study routine with revision intervals.",
                content = """
                    # Daily Study Routine & Habit Tracker
                    
                    • **Slot 1 (5:30 PM - 6:45 PM): Physics**
                      - 30 mins: Concept theory & NCERT reading
                      - 45 mins: Derivation practice & numerical solving
                    
                    • **Break (6:45 PM - 7:00 PM): Quick Refreshment**
                    
                    • **Slot 2 (7:00 PM - 8:15 PM): Chemistry**
                      - Physical: Problem practice
                      - Organic: Reaction mechanism & nomenclature practice
                      - Inorganic: Periodic table revision & NCERT lines
                    
                    • **Dinner Break (8:15 PM - 9:00 PM)**
                    
                    • **Slot 3 (9:00 PM - 10:30 PM): Mathematics**
                      - 15 mins: Formula review
                      - 75 mins: Pen-and-paper exercise questions & proofs
                    
                    • **Night Wrap-up (10:30 PM - 10:45 PM)**:
                      - Review flashcards / formula sheets in Naitik Jain App
                      - Plan next day topics
                """.trimIndent(),
                isSample = true
            )
        )
    }
}
