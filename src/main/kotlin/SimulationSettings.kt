import org.openrndr.extra.parameters.Description
import org.openrndr.extra.parameters.DoubleParameter
import kotlin.math.sqrt

@Description("Live simulation settings")
object SimulationSettings {
    @DoubleParameter("epsilon", 0.0, 100.0, precision = 1)
    var epsilon = 1.0

    @DoubleParameter("Max force", 0.0, 10000.0, precision = 0)
    var maxForce = 10000.0

    @DoubleParameter("Max velocity", 0.0, 10000.0, precision = 0)
    var maxVelocity = 10000.0

    @DoubleParameter("deltaT", 0.0, 0.1, precision = 4)
    var deltaT = 0.001// SimulationConstants.sigma / sqrt(epsilon)

    @DoubleParameter("Starting velocity", 0.0, 10000.0, precision = 0)
    var startingVelocity = 10.0

    var step = 0
}
