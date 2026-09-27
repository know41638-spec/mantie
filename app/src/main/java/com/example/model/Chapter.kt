package com.example.model

data class Chapter(
    val subjectId: String,
    val number: Int,
    val title: String,
    val description: String,
    val keyTopics: List<String>,
    val quickFormulas: List<String> = emptyList()
)

object CurriculumData {
    val physicsChapters = listOf(
        Chapter(
            subjectId = "PHYSICS",
            number = 1,
            title = "Units and Measurements",
            description = "SI units, dimensional analysis, errors in measurement, and significant figures.",
            keyTopics = listOf("Fundamental & Derived Units", "Dimensional Formulae", "Error Analysis & Propagation", "Significant Figures"),
            quickFormulas = listOf(
                "Absolute Error: Δa = |a - a_mean|",
                "Relative Error = (Δa_mean / a_mean)",
                "Percentage Error = Relative Error × 100%",
                "Dimensional Homogeneity: [LHS] = [RHS]"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 2,
            title = "Motion in a Straight Line",
            description = "Kinematics in 1D, position-time graphs, equations of uniformly accelerated motion.",
            keyTopics = listOf("Distance & Displacement", "Speed & Velocity", "Acceleration & Graphs", "Equations of Motion under Gravity"),
            quickFormulas = listOf(
                "v = u + at",
                "s = ut + ½at²",
                "v² = u² + 2as",
                "s_nth = u + a/2 (2n - 1)"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 3,
            title = "Motion in a Plane",
            description = "Vectors, resolution, relative velocity, and projectile motion.",
            keyTopics = listOf("Vector Addition & Dot/Cross Product", "Projectile Motion", "Horizontal & Oblique Projections", "Uniform Circular Motion"),
            quickFormulas = listOf(
                "Max Height: H = (u² sin²θ) / (2g)",
                "Time of Flight: T = (2u sinθ) / g",
                "Range: R = (u² sin2θ) / g",
                "Centripetal Accel: a_c = v² / r = ω²r"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 4,
            title = "Laws of Motion",
            description = "Newton's laws, inertia, momentum, impulse, friction, and circular dynamics.",
            keyTopics = listOf("Newton's 3 Laws", "Impulse-Momentum Theorem", "Static & Kinetic Friction", "Banking of Curved Roads"),
            quickFormulas = listOf(
                "F = dp/dt = ma",
                "Impulse: J = F·Δt = Δp",
                "Frictional Force: f_s ≤ μ_s N, f_k = μ_k N",
                "Optimum Banking Speed: v = √(r g tanθ)"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 5,
            title = "Work, Energy and Power",
            description = "Work-energy theorem, conservative forces, potential energy, and collisions.",
            keyTopics = listOf("Work Done by Variable Force", "Work-Energy Theorem", "Elastic & Inelastic Collisions", "Power & Efficiency"),
            quickFormulas = listOf(
                "W = ∫ F · dr = F d cosθ",
                "Work-Energy: W_net = ΔK = ½mv² - ½mu²",
                "Power: P = dW/dt = F · v",
                "Spring Potential Energy: U = ½ k x²"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 6,
            title = "System of Particles & Rotational Motion",
            description = "Center of mass, torque, angular momentum, and moment of inertia.",
            keyTopics = listOf("Center of Mass Calculation", "Torque & Couple", "Moment of Inertia & Theorems", "Conservation of Angular Momentum"),
            quickFormulas = listOf(
                "Torque: τ = r × F = I α",
                "Angular Momentum: L = r × p = I ω",
                "Rotational K.E. = ½ I ω²",
                "Parallel Axis Theorem: I = I_cm + M d²"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 7,
            title = "Gravitation",
            description = "Kepler's laws, universal law of gravitation, gravitational potential, and orbital velocity.",
            keyTopics = listOf("Newton's Gravitational Law", "Variation of 'g' with Altitude & Depth", "Escape Velocity", "Satellite Motion & Orbital Velocity"),
            quickFormulas = listOf(
                "F = G m₁m₂ / r²",
                "g_h = g(1 - 2h/R), g_d = g(1 - d/R)",
                "Escape Velocity: v_e = √(2 g R)",
                "Orbital Velocity: v_o = √(G M / r)"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 8,
            title = "Mechanical Properties of Solids",
            description = "Elastic behavior, stress-strain relationship, Hooke's law, and Young's modulus.",
            keyTopics = listOf("Stress & Strain Curves", "Young's, Bulk & Shear Modulus", "Poisson's Ratio", "Elastic Energy Density"),
            quickFormulas = listOf(
                "Hooke's Law: Stress = Y × Strain",
                "Young's Modulus: Y = (F/A) / (ΔL/L)",
                "Energy Density = ½ × Stress × Strain"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 9,
            title = "Mechanical Properties of Fluids",
            description = "Pressure, Pascal's principle, viscosity, Bernoulli's theorem, and surface tension.",
            keyTopics = listOf("Pascal's & Archimedes' Laws", "Equation of Continuity", "Bernoulli's Theorem", "Viscosity & Stokes' Law", "Surface Tension & Capillarity"),
            quickFormulas = listOf(
                "Bernoulli: P + ½ρv² + ρgh = Constant",
                "Equation of Continuity: A₁v₁ = A₂v₂",
                "Stokes' Law: F = 6πηrv",
                "Terminal Velocity: v_t = 2r²(ρ - σ)g / (9η)"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 10,
            title = "Thermal Properties of Matter",
            description = "Heat, temperature, thermal expansion, specific heat capacity, calorimetry, and heat transfer.",
            keyTopics = listOf("Thermal Expansion (α, β, γ)", "Calorimetry Principle", "Stefan's Law & Newton's Law of Cooling", "Wien's Displacement Law"),
            quickFormulas = listOf(
                "Linear Expansion: ΔL = L₀ α ΔT",
                "Heat: Q = m c ΔT = m L",
                "Newton's Cooling: -dT/dt = k(T - T₀)",
                "Wien's Law: λ_max · T = b"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 11,
            title = "Thermodynamics",
            description = "Thermal equilibrium, zeroth, first, and second laws of thermodynamics, heat engines.",
            keyTopics = listOf("First Law of Thermodynamics", "Isothermal, Adiabatic, Isochoric Processes", "Second Law & Carnot Engine", "Enthalpy & Heat Capacity"),
            quickFormulas = listOf(
                "First Law: ΔQ = ΔU + ΔW",
                "Work in Isothermal: W = nRT ln(V₂/V₁)",
                "Work in Adiabatic: W = (P₁V₁ - P₂V₂) / (γ - 1)",
                "Carnot Efficiency: η = 1 - (T₂/T₁)"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 12,
            title = "Kinetic Theory",
            description = "Molecular nature of matter, ideal gas equation, kinetic pressure, and degrees of freedom.",
            keyTopics = listOf("Assumptions of Kinetic Theory", "Pressure of an Ideal Gas", "Law of Equipartition of Energy", "Mean Free Path"),
            quickFormulas = listOf(
                "Ideal Gas: P V = n R T",
                "Kinetic Pressure: P = ⅓ ρ v_rms²",
                "v_rms = √(3RT / M)",
                "Internal Energy: U = (f/2) n R T"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 13,
            title = "Oscillations",
            description = "Periodic motion, Simple Harmonic Motion (SHM), spring-mass systems, and simple pendulum.",
            keyTopics = listOf("Characteristics of SHM", "Velocity & Acceleration in SHM", "Energy in SHM", "Time Period of Simple Pendulum & Springs"),
            quickFormulas = listOf(
                "x = A sin(ωt + φ)",
                "v = ω √(A² - x²)",
                "a = -ω² x",
                "Simple Pendulum: T = 2π √(L / g)",
                "Spring Pendulum: T = 2π √(m / k)"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 14,
            title = "Waves",
            description = "Transverse and longitudinal waves, speed of sound, principle of superposition, and standing waves.",
            keyTopics = listOf("Wave Speed in Stretched Strings", "Speed of Sound (Laplace Correction)", "Standing Waves in Strings & Pipes", "Beats Phenomenon"),
            quickFormulas = listOf(
                "Wave Speed: v = f λ = √(T / μ)",
                "Sound Speed: v = √(γ P / ρ)",
                "Beat Frequency: f_beat = |f₁ - f₂|",
                "Open Organ Pipe: f_n = n(v / 2L)"
            )
        ),
        Chapter(
            subjectId = "PHYSICS",
            number = 15,
            title = "Physical World & Mathematical Tools",
            description = "Fundamental forces in nature, conservation principles, calculus basics, and trigonometry.",
            keyTopics = listOf("Fundamental Forces", "Differentiation & Integration Rules", "Vector Algebra Revision", "Logarithmic & Exponential Calculations"),
            quickFormulas = listOf(
                "d/dx (xⁿ) = n xⁿ⁻¹",
                "∫ xⁿ dx = (xⁿ⁺¹) / (n+1) + C",
                "Vector Dot: A · B = |A||B| cosθ",
                "Vector Cross: |A × B| = |A||B| sinθ"
            )
        )
    )

    val chemistryChapters = listOf(
        Chapter(
            subjectId = "CHEMISTRY",
            number = 1,
            title = "Some Basic Concepts of Chemistry",
            description = "Mole concept, stoichiometry, atomic and molecular masses, and concentration terms.",
            keyTopics = listOf("Mole Concept & Avogadro's Number", "Empirical & Molecular Formula", "Molarity, Molality & Normality", "Limiting Reagent"),
            quickFormulas = listOf(
                "Moles (n) = Given mass (g) / Molar mass (g/mol)",
                "Molarity (M) = Moles of solute / Liters of solution",
                "Molality (m) = Moles of solute / kg of solvent",
                "Mole Fraction: X_A = n_A / (n_A + n_B)"
            )
        ),
        Chapter(
            subjectId = "CHEMISTRY",
            number = 2,
            title = "Structure of Atom",
            description = "Subatomic particles, Bohr's model, de Broglie relation, Heisenberg uncertainty, and quantum numbers.",
            keyTopics = listOf("Bohr's Postulates & Hydrogen Spectrum", "de Broglie Wavelength", "Heisenberg Uncertainty Principle", "Quantum Numbers (n, l, m, s)", "Aufbau, Hund & Pauli Principles"),
            quickFormulas = listOf(
                "Photon Energy: E = hν = hc / λ",
                "de Broglie: λ = h / (m v)",
                "Heisenberg: Δx · Δp ≥ h / (4π)",
                "Bohr Radius: r_n = 0.529 (n² / Z) Å"
            )
        ),
        Chapter(
            subjectId = "CHEMISTRY",
            number = 3,
            title = "Classification of Elements & Periodicity",
            description = "Modern periodic table, electronic configurations, and periodic trends in properties.",
            keyTopics = listOf("Modern Periodic Law", "Atomic & Ionic Radii Trends", "Ionization Enthalpy & Electron Gain Enthalpy", "Electronegativity (Pauling Scale)"),
            quickFormulas = listOf(
                "Effective Nuclear Charge: Z_eff = Z - σ",
                "Ionization Enthalpy: IE₁ < IE₂ < IE₃",
                "Electronegativity trend: increases across period, decreases down group"
            )
        ),
        Chapter(
            subjectId = "CHEMISTRY",
            number = 4,
            title = "Chemical Bonding & Molecular Structure",
            description = "Ionic and covalent bonds, VSEPR theory, hybridization, dipole moment, and MO theory.",
            keyTopics = listOf("Lewis Structures & Formal Charge", "VSEPR Shapes of Molecules", "Hybridization (sp, sp², sp³, dsp²)", "Molecular Orbital Theory & Bond Order"),
            quickFormulas = listOf(
                "Formal Charge = V - L - ½ S",
                "Dipole Moment: μ = q × d",
                "Bond Order = ½ (N_b - N_a)",
                "Stability ∝ Bond Order"
            )
        ),
        Chapter(
            subjectId = "CHEMISTRY",
            number = 5,
            title = "Chemical Thermodynamics",
            description = "First and second laws, internal energy, enthalpy, Hess's law, entropy, and Gibbs free energy.",
            keyTopics = listOf("First Law: ΔU = q + w", "Enthalpy & Heat Capacities (Cp, Cv)", "Hess's Law of Constant Heat Summation", "Gibbs Free Energy & Spontaneity"),
            quickFormulas = listOf(
                "ΔH = ΔU + Δn_g R T",
                "Gibbs Free Energy: ΔG = ΔH - T ΔS",
                "Spontaneity criterion: ΔG < 0",
                "Equilibrium: ΔG° = -2.303 RT log K"
            )
        ),
        Chapter(
            subjectId = "CHEMISTRY",
            number = 6,
            title = "Equilibrium",
            description = "Chemical and ionic equilibrium, Le Chatelier's principle, pH scale, buffers, and solubility product.",
            keyTopics = listOf("Law of Mass Action (Kc & Kp)", "Le Chatelier's Principle", "pH, Acids and Bases (Arrhenius, Bronsted, Lewis)", "Buffer Solutions & Ksp"),
            quickFormulas = listOf(
                "Kp = Kc (RT)^Δn_g",
                "pH = -log[H⁺], pOH = -log[OH⁻]",
                "pH + pOH = 14 (at 298 K)",
                "Henderson-Hasselbalch: pH = pKa + log([Salt]/[Acid])"
            )
        ),
        Chapter(
            subjectId = "CHEMISTRY",
            number = 7,
            title = "Redox Reactions",
            description = "Oxidation number concepts, balancing redox equations, and electrochemical cells.",
            keyTopics = listOf("Oxidation States Rules", "Balancing by Ion-Electron Method", "Balancing by Oxidation Number Method", "Electrochemical Series & Applications"),
            quickFormulas = listOf(
                "Oxidation: Increase in Oxidation Number (Loss of electrons)",
                "Reduction: Decrease in Oxidation Number (Gain of electrons)",
                "Oxidizing Agent gets reduced; Reducing Agent gets oxidized"
            )
        ),
        Chapter(
            subjectId = "CHEMISTRY",
            number = 8,
            title = "Organic Chemistry – Some Basic Principles",
            description = "IUPAC nomenclature, electronic displacements (inductive, electromeric, resonance, hyperconjugation), and reaction intermediates.",
            keyTopics = listOf("IUPAC Naming of Functional Groups", "Inductive & Resonance Effects (+I/-I, +M/-M)", "Hyperconjugation & Carbocation Stability", "Carbocations, Carbanions & Free Radicals"),
            quickFormulas = listOf(
                "Carbocation Stability: 3° > 2° > 1° > methyl",
                "Carbanion Stability: methyl > 1° > 2° > 3°",
                "Free Radical Stability: 3° > 2° > 1°"
            )
        ),
        Chapter(
            subjectId = "CHEMISTRY",
            number = 9,
            title = "Hydrocarbons",
            description = "Alkanes, alkenes, alkynes, and aromatic hydrocarbons; preparations, properties, and reactions.",
            keyTopics = listOf("Alkanes: Wurtz Reaction & Free Radical Halogenation", "Alkenes: Markovnikov & Anti-Markovnikov Rule", "Alkynes: Acidity & Polymerization", "Benzene: Huckel's Rule (4n+2) & Electrophilic Substitution"),
            quickFormulas = listOf(
                "Markovnikov Rule: H⁺ adds to carbon with more H's",
                "Kharasch/Peroxide Effect: Anti-Markovnikov with HBr + peroxide",
                "Hückel's Rule: (4n+2) π electrons for aromaticity"
            )
        ),
        Chapter(
            subjectId = "CHEMISTRY",
            number = 10,
            title = "States of Matter & Solutions (Foundation)",
            description = "Gas laws, intermolecular forces, Dalton's law, and foundational solution concepts.",
            keyTopics = listOf("Boyle's, Charles's & Avogadro's Laws", "Dalton's Law of Partial Pressures", "Real Gas & van der Waals Equation", "Compressibility Factor (Z)"),
            quickFormulas = listOf(
                "van der Waals: (P + a n²/V²)(V - n b) = n R T",
                "Dalton's Law: P_total = P₁ + P₂ + ...",
                "Compressibility Factor: Z = PV / (nRT)"
            )
        )
    )

    val mathsChapters = listOf(
        Chapter(
            subjectId = "MATHS",
            number = 1,
            title = "Sets",
            description = "Sets and their representations, empty set, subsets, power set, Venn diagrams, and set operations.",
            keyTopics = listOf("Roster & Set-Builder Notation", "Subsets & Power Sets (2ⁿ elements)", "Venn Diagrams & Union/Intersection", "De Morgan's Laws"),
            quickFormulas = listOf(
                "n(A ∪ B) = n(A) + n(B) - n(A ∩ B)",
                "n(A ∪ B ∪ C) = n(A) + n(B) + n(C) - n(A∩B) - n(B∩C) - n(C∩A) + n(A∩B∩C)",
                "De Morgan: (A ∪ B)' = A' ∩ B', (A ∩ B)' = A' ∪ B'"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 2,
            title = "Relations and Functions",
            description = "Cartesian products, relations, domain, range, codomain, and standard functions.",
            keyTopics = listOf("Cartesian Product A × B", "Definition of Relations & Functions", "Domain & Range of Real Functions", "Greatest Integer, Modulus & Signum Functions"),
            quickFormulas = listOf(
                "If n(A)=p, n(B)=q then n(A×B) = pq",
                "Total Relations from A to B = 2^(pq)",
                "f(x) = |x| = { x if x ≥ 0, -x if x < 0 }"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 3,
            title = "Trigonometric Functions",
            description = "Angles, radian measure, trigonometric identities, sum and difference formulas, and multiple angle formulas.",
            keyTopics = listOf("Radian and Degree Conversion", "ASTC Rule (All-Silver-Tea-Cups)", "Compound Angle Identities", "Transformation & Multiple Angle Formulas"),
            quickFormulas = listOf(
                "1 rad = (180/π)°, 1° = (π/180) rad",
                "sin(A ± B) = sinA cosB ± cosA sinB",
                "cos(A ± B) = cosA cosB ∓ sinA sinB",
                "cos 2θ = cos²θ - sin²θ = 2cos²θ - 1 = 1 - 2sin²θ",
                "sin 2θ = 2 sinθ cosθ"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 4,
            title = "Complex Numbers & Quadratic Equations",
            description = "Imaginary units, algebraic properties, modulus and conjugate, and quadratic equations with complex roots.",
            keyTopics = listOf("Powers of i (i² = -1)", "Modulus |z| and Conjugate z̄", "Multiplicative Inverse", "Roots of ax² + bx + c = 0 when D < 0"),
            quickFormulas = listOf(
                "z = a + ib, |z| = √(a² + b²)",
                "z · z̄ = |z|²",
                "z⁻¹ = z̄ / |z|²",
                "x = (-b ± i√|D|) / (2a) when D < 0"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 5,
            title = "Linear Inequalities",
            description = "Linear inequalities in one and two variables, algebraic and graphical solutions.",
            keyTopics = listOf("Rules of Inequalities (Sign inversion upon negative multiplication)", "Solving Inequalities in One Variable", "Graphical Solution on Number Line", "System of Linear Inequalities in Two Variables"),
            quickFormulas = listOf(
                "If a < b and c < 0, then ac > bc",
                "|x| < a ⇒ -a < x < a",
                "|x| > a ⇒ x < -a or x > a"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 6,
            title = "Permutations and Combinations",
            description = "Fundamental principles of counting, factorial notation, permutations (ⁿPᵣ), and combinations (ⁿCᵣ).",
            keyTopics = listOf("Multiplication & Addition Principles", "Factorial (n!) Properties", "Permutations: ⁿPᵣ = n! / (n-r)!", "Combinations: ⁿCᵣ = n! / [r! (n-r)!]"),
            quickFormulas = listOf(
                "ⁿPᵣ = n! / (n - r)!",
                "ⁿCᵣ = n! / [r!(n - r)!]",
                "ⁿCᵣ = ⁿCₙ₋ᵣ",
                "ⁿCᵣ + ⁿCᵣ₋₁ = ⁿ⁺¹Cᵣ"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 7,
            title = "Binomial Theorem",
            description = "Binomial expansion for positive integral indices, general and middle terms.",
            keyTopics = listOf("Statement of Binomial Theorem", "General Term (T_r+1)", "Finding Middle Terms", "Properties of Binomial Coefficients"),
            quickFormulas = listOf(
                "(a + b)ⁿ = ∑ ⁿCᵣ aⁿ⁻ʳ bʳ (r = 0 to n)",
                "General Term: T_(r+1) = ⁿCᵣ aⁿ⁻ʳ bʳ",
                "Total number of terms = n + 1",
                "Sum of coefficients = 2ⁿ"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 8,
            title = "Sequences and Series",
            description = "Arithmetic progressions, geometric progressions, arithmetic mean, geometric mean, and relation between AM and GM.",
            keyTopics = listOf("A.P.: nth term and sum of n terms", "G.P.: nth term and sum of n terms", "Sum of Infinite G.P.", "AM ≥ GM Inequality"),
            quickFormulas = listOf(
                "AP: a_n = a + (n-1)d, S_n = n/2 [2a + (n-1)d]",
                "GP: a_n = a rⁿ⁻¹, S_n = a(rⁿ - 1)/(r - 1)",
                "Infinite GP: S_∞ = a / (1 - r) for |r| < 1",
                "AM = (a+b)/2, GM = √(ab) ⇒ AM ≥ GM"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 9,
            title = "Straight Lines",
            description = "Slope of a line, various forms of equations of a line, distance of a point from a line.",
            keyTopics = listOf("Slope m = tanθ = (y₂-y₁)/(x₂-x₁)", "Slope-Intercept & Point-Slope Forms", "Two-Point & Intercept Forms", "Perpendicular Distance Formula"),
            quickFormulas = listOf(
                "Point-Slope: y - y₁ = m(x - x₁)",
                "Slope-Intercept: y = mx + c",
                "Intercept Form: x/a + y/b = 1",
                "Perpendicular Distance: d = |Ax₁ + By₁ + C| / √(A² + B²)"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 10,
            title = "Conic Sections",
            description = "Sections of a cone: circles, parabola, ellipse, and hyperbola with standard equations.",
            keyTopics = listOf("Circle: (x-h)² + (y-k)² = r²", "Parabola (y² = 4ax, x² = 4ay)", "Ellipse: x²/a² + y²/b² = 1 (e < 1)", "Hyperbola: x²/a² - y²/b² = 1 (e > 1)"),
            quickFormulas = listOf(
                "Circle Center (h,k), Radius r",
                "Parabola: Focus (a, 0), Directrix x = -a, Latus Rectum = 4a",
                "Ellipse: c² = a² - b², e = c/a, Latus Rectum = 2b²/a",
                "Hyperbola: c² = a² + b², e = c/a, Latus Rectum = 2b²/a"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 11,
            title = "Introduction to Three Dimensional Geometry",
            description = "Coordinate axes and planes in 3D, coordinates of a point, distance and section formulas.",
            keyTopics = listOf("Octants in 3D Space", "Distance Formula in 3D", "Section Formula in 3D", "Centroid of a Triangle"),
            quickFormulas = listOf(
                "Distance: d = √[(x₂-x₁)² + (y₂-y₁)² + (z₂-z₁)²]",
                "Internal Division: [(mx₂+nx₁)/(m+n), (my₂+ny₁)/(m+n), (mz₂+nz₁)/(m+n)]",
                "Centroid: [(x₁+x₂+x₃)/3, (y₁+y₂+y₃)/3, (z₁+z₂+z₃)/3]"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 12,
            title = "Limits and Derivatives",
            description = "Intuitive idea of limits, standard limits, derivatives by first principle, product and quotient rules.",
            keyTopics = listOf("Concept of Left & Right Hand Limits", "Standard Trigonometric Limits", "Derivative by First Principle", "Product & Quotient Rules"),
            quickFormulas = listOf(
                "lim (x→a) (xⁿ - aⁿ)/(x - a) = n aⁿ⁻¹",
                "lim (x→0) (sin x / x) = 1",
                "First Principle: f'(x) = lim(h→0) [f(x+h) - f(x)] / h",
                "Product Rule: (uv)' = u'v + uv'",
                "Quotient Rule: (u/v)' = (u'v - uv') / v²"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 13,
            title = "Statistics",
            description = "Measures of dispersion: range, mean deviation, variance, and standard deviation of grouped and ungrouped data.",
            keyTopics = listOf("Mean Deviation about Mean & Median", "Variance (σ²) Calculation", "Standard Deviation (σ)", "Coefficient of Variation (CV)"),
            quickFormulas = listOf(
                "Mean Deviation = (∑ |xᵢ - x̄|) / N",
                "Variance: σ² = (∑ (xᵢ - x̄)²) / N",
                "Standard Deviation: σ = √(Variance)",
                "Coefficient of Variation: CV = (σ / x̄) × 100"
            )
        ),
        Chapter(
            subjectId = "MATHS",
            number = 14,
            title = "Probability",
            description = "Random experiments, sample space, events, mutually exclusive and exhaustive events, axiomatic probability.",
            keyTopics = listOf("Sample Spaces & Events", "Mutually Exclusive Events (A ∩ B = ∅)", "Exhaustive Events (A ∪ B = S)", "Addition Rule of Probability"),
            quickFormulas = listOf(
                "P(A) = n(A) / n(S)",
                "P(A ∪ B) = P(A) + P(B) - P(A ∩ B)",
                "If A and B are mutually exclusive: P(A ∪ B) = P(A) + P(B)",
                "P(A') = 1 - P(A)"
            )
        )
    )

    val otherCategories = listOf(
        "📝 School Notes",
        "📄 Question Papers",
        "📚 Reference Material",
        "🎥 Important Resources",
        "📌 Other"
    )
}
