import { CssBaseline, AppBar, Toolbar, Typography, Box, Link, Container, Grid, Stack, Accordion, AccordionSummary, AccordionDetails, Avatar, Divider } from "@mui/material"
import { Timeline, TimelineItem, TimelineSeparator, TimelineConnector, TimelineContent, TimelineDot, TimelineOppositeContent } from '@mui/lab';
import AvatarCard from "./components/AvatarCard";
import MembersInfo from "./team";
import Deliveries from "./deliveries";
import FileCard from "./components/FileCard";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import GitHubIcon from '@mui/icons-material/GitHub';
import ArticleIcon from '@mui/icons-material/Article';
import CreateIcon from '@mui/icons-material/Create';
import Logo from "./assets/images/logo.png"
import TimelineInfo from "./timeline"
import GoalCard from "./components/GoalCard";
import Goals from "./goals";
import Youtube from 'react-youtube';

function App() {

  return (
    <>
      <CssBaseline />

      {/* Navbar */}
      <AppBar position="fixed">
        <Toolbar>
          <Link href="#" color="white" underline="none" sx={{ mr: 3 }}>
            <Stack direction="row" alignItems="center" spacing={2}>
              <Avatar alt="logo" src={Logo} />
              <Typography variant="h6">GrowMate</Typography>
            </Stack>
          </Link>
          <Stack direction="row" alignItems="center" spacing={2}>
            <Link href="#goals" color="white" variant="body1" underline="hover">Goals</Link>
            <Link href="#team" color="white" variant="body1" underline="hover">Team</Link>
            <Link href="#timeline" color="white" variant="body1" underline="hover">Timeline</Link>
            <Link href="#deliveries" color="white" variant="body1" underline="hover">Deliveries</Link>
          </Stack>
          <Link href="https://github.com/Sensing-My-Home/Growmate" color="white" underline="hover" sx={{ flexGrow: 1, marginRight: 3 }}>
            <Stack direction="row" alignItems="center" justifyContent="flex-end" spacing={1}>
              <GitHubIcon />
              <Typography variant="body1">Repository</Typography>
            </Stack>
          </Link>
          <Link href="https://sensing-my-home.github.io/Documentation/" color="white" underline="hover" sx={{ marginRight: 3 }}>
            <Stack direction="row" alignItems="center" justifyContent="flex-end" spacing={1}>
              <ArticleIcon />
              <Typography variant="body1">Documentation</Typography>
            </Stack>
          </Link>
          <Link href="https://documenter.getpostman.com/view/24060738/2s93m8xKtK" color="white" underline="hover">
            <Stack direction="row" alignItems="center" justifyContent="flex-end" spacing={1}>
              <CreateIcon />
              <Typography variant="body1">API</Typography>
            </Stack>
          </Link>
        </Toolbar>
      </AppBar>
      {/* End of Navbar */}

      <main>
        {/* Hero Unit */}
        <Box
          sx={{
            bgcolor: 'background.paper',
            pt: 15,
            pb: 6,
          }}
        >
          <Grid container spacing={8} direction="row" justifyContent="center" alignItems="center">
            <Grid item xs={12} sm={6} md={6}>
              <Typography
                component="h1"
                variant="h2"
                align="center"
                color="text.primary"
                gutterBottom
              >
                GrowMate
              </Typography>
              <Typography variant="h5" align="center" color="text.secondary" paragraph>
                Mobile App to monitor the health and growth of plants in a domestic environment. Provides helpful tips and recommendations to ensure their well-being.
              </Typography>
            </Grid>
            <Grid item xs={12} sm={6} md={6}>
              <Youtube videoId="uP0-BJbaTyA" />
            </Grid>
          </Grid>
        </Box>
        {/* End of Hero Unit */}

        <Divider variant="middle" />

        {/* Goals */}
        <Container maxWidth="md" sx={{ pt: 10, pb: 6 }} id="goals">
          <Typography
            component="h1"
            variant="h2"
            align="center"
            color="text.primary"
            gutterBottom
          >
            Goals
          </Typography>
          <Grid container direction="row" justifyContent="center" alignItems="center" spacing={3}>
            {Goals.map((goal, index) => {
              return (
                <Grid item key={index} xs={6} sm={6} md={4}>
                  <GoalCard icon={goal.icon} title={goal.title} description={goal.description} />
                </Grid>
              )
            })}
          </Grid>
        </Container>
        {/* End of Goals */}

        <Divider variant="middle" />

        {/* Project Team */}
        <Container maxWidth="md" sx={{ pt: 10, pb: 6 }} id="team">
          <Typography
            component="h1"
            variant="h2"
            align="center"
            color="text.primary"
            gutterBottom
          >
            Team
          </Typography>
          <Grid container direction="row" justifyContent="center" alignItems="center" spacing={3}>
            {MembersInfo.map((member, index) => {
              return (
                <Grid item key={index} xs={6} sm={6} md={4}>
                  <AvatarCard name={member.name} email={member.email} description={member.description} image={member.image} linkedin={member.linkedin} github={member.github} />
                </Grid>
              )
            })}
          </Grid>
        </Container>
        {/* End of Project Team */}

        <Divider variant="middle" />

        {/* Timeline */}
        <Container maxWidth="md" sx={{ pt: 10, pb: 6 }} id="timeline">
          <Typography
            component="h1"
            variant="h2"
            align="center"
            color="text.primary"
            gutterBottom
          >
            Timeline
          </Typography>
          <Stack direction="row" spacing={2} justifyContent="center">
            <Stack direction="row" alignItems="center" spacing={1}>
              <TimelineDot color="grey" />
              <Typography>To Do</Typography>
            </Stack>
            <Stack direction="row" alignItems="center" spacing={1}>
              <TimelineDot color="secondary" />
              <Typography>In Progress</Typography>
            </Stack>
            <Stack direction="row" alignItems="center" spacing={1}>
              <TimelineDot color="primary" />
              <Typography>Done</Typography>
            </Stack>
          </Stack>
          <Timeline position="alternate">
            {TimelineInfo.map((info, index) => {
              return (
                <TimelineItem key={index}>
                  <TimelineOppositeContent color="text.secondary">
                    {info.date}
                  </TimelineOppositeContent>
                  <TimelineSeparator>
                    <TimelineDot color={info.color} />
                    <TimelineConnector />
                  </TimelineSeparator>
                  <TimelineContent>
                    <Typography variant="h6" component="span">{info.title}</Typography>
                    <Typography>{info.description}</Typography>
                  </TimelineContent>
                </TimelineItem>
              )
            })}
          </Timeline>
        </Container>
        {/* End of Timeline */}

        <Divider variant="middle" />

        {/* Deliveries */}
        <Container maxWidth="md" sx={{ pt: 10, pb: 6 }} id="deliveries">
          <Typography
            component="h1"
            variant="h2"
            align="center"
            color="text.primary"
            gutterBottom
          >
            Deliveries
          </Typography>
          {Deliveries.map(((delivery, index) => {
            return (
              <Accordion key={index} disabled={delivery.state}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                  <Typography>{delivery.name}</Typography>
                </AccordionSummary>
                <AccordionDetails>
                  <Grid container spacing={3} justifyContent="center">
                    {delivery.data.map(((file, i) => {
                      return (
                        <Grid item xs={12} sm={6} md={4} key={i}>
                          <FileCard name={file.name} link={file.link} icon={file.icon} />
                        </Grid>
                      )
                    }))}
                  </Grid>
                </AccordionDetails>
              </Accordion>
            )
          }))}
        </Container>
        {/* End of Deliveries */}
      </main>

      {/* Footer */}
      <Box sx={{ bgcolor: 'background.paper', p: 5 }} component="footer">
        <Typography variant="h6" align="center" gutterBottom>
          Projeto em Informática (3ºAno LEI)
        </Typography>
        <Typography
          variant="subtitle1"
          align="center"
        >
          <Link href="https://www.ua.pt/" color="inherit">Universidade de Aveiro</Link>
        </Typography>
      </Box>
      {/* End of Footer */}
    </>
  )
}

export default App
