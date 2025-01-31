package me.dvyy.particles

import me.dvyy.particles.dsl.ParticlesConfiguration
import me.dvyy.particles.extensions.CustomCamera2D
import me.dvyy.particles.extensions.FPSDisplay
import org.openrndr.Fullscreen
import org.openrndr.PresentationMode
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.color.presets.DIM_GRAY
import org.openrndr.extra.gui.GUI
import org.openrndr.extra.gui.GUIAppearance
import org.openrndr.panel.style.defaultStyles

data class FieldsApplication(
    val config: ParticlesConfiguration,
) {
    fun start() = application {
        configure {
            fullscreen = if(config.application.fullscreen) Fullscreen.CURRENT_DISPLAY_MODE else Fullscreen.DISABLED
            windowResizable = true
            width = config.application.width
            height = config.application.height
            vsync = false
        }

        program {
            window.presentationMode = PresentationMode.MANUAL
            // Create simulation settings and attach to the gui
            val gui = GUI(
                appearance = GUIAppearance(
                    baseColor = ColorRGBa.DIM_GRAY,
                ),
                defaultStyles = defaultStyles(
                    controlFontSize = 17.0,
                )
            ).apply {
                compartmentsCollapsedByDefault = false

                add(SimulationConstants)
                add(SimulationSettings)
            }
            extend(CustomCamera2D(gui = gui))
            extend(gui) // Load saved values right away
            extend(FPSDisplay(gui) { SimulationSettings.step })
            extend(FieldsGPU(drawer.bounds, config))
        }
    }
}
