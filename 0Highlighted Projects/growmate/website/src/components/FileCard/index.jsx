import { Card, CardContent, Stack, Typography, Link, Tooltip } from "@mui/material";
import PictureAsPdfIcon from '@mui/icons-material/PictureAsPdf';
import TvIcon from '@mui/icons-material/Tv';

function getFileIcon(icon) {
    switch(icon) {
        case 'PDF':
            return (<PictureAsPdfIcon fontSize="large"/>);
        default:
            return (<TvIcon fontSize="large"/>)
    }
}

function FileCard({ name, link, icon }) {
    return (
        <Tooltip title="Click to Download" arrow>
            <Link download href={link} underline="hover">
                <Card>
                    <CardContent>
                        <Stack alignItems="center">
                            {getFileIcon(icon)}
                            <Typography variant="body1">{name}</Typography>
                        </Stack>
                    </CardContent>
                </Card>
            </Link>
        </Tooltip>
    )
}

export default FileCard;