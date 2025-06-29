import MS1Presentation from './assets/presentations/MS1_Presentation.pptx'
import MS2Presentation from './assets/presentations/MS2_Presentation.pptx'
import MS3Presentation from './assets/presentations/MS3_Presentation.pptx'
import MS4Presentation from './assets/presentations/MS4_Presentation.pptx'

import Report from './assets/docs/Report.pdf'

const Deliveries = [
    {
        name: "M1 - Lifecycle objectives and calendar for the project",
        state: false,
        data: [
            {
                name: "MS1-Presentation",
                link: MS1Presentation,
                icon: "TV",
            },
        ]
    },
    {
        name: "M2 - Lifecycle architecture",
        state: false,
        data: [
            {
                name: "MS2-Presentation",
                link: MS2Presentation,
                icon: "TV"
            },
        ]
    },
    {
        name: "M3 - Prototype",
        state: false,
        data: [
            {
                name: "Presentation",
                link: MS3Presentation,
                icon: "TV"
            },
        ]
    },
    {
        name: "M4 - Final product",
        state: false,
        data: [
            {
                name: "Presentation",
                link: MS4Presentation,
                icon: "TV"
            },
            {
                name: "Report",
                link: Report,
                icon: "PDF"
            },
        ]
    }
]

export default Deliveries;