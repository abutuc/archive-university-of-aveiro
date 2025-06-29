import { Avatar, Typography, Stack, Link } from '@mui/material'
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import GitHubIcon from '@mui/icons-material/GitHub';

function AvatarCard({ name, email, description, image, linkedin, github }) {
    return(
        <>
            <Stack
                justifyContent="center"
                alignItems="center"
            >
                <Avatar alt={name} src={image} sx={{width: 125, height: 125}}/>
                <Typography variant="h6">{name}</Typography>
                <Typography variant="body1">{description}</Typography>
                <Typography variant="body1">{email}</Typography>
                <Stack direction="row" spacing={1}>
                    <Link href={linkedin} color="primary"><LinkedInIcon /></Link>
                    <Link href={github} color="inherit"><GitHubIcon /></Link>
                </Stack>
            </Stack>
        </>
    )
}

export default AvatarCard