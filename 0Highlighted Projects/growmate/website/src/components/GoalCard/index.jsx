import { Card, CardContent, Typography, Stack, Icon } from "@mui/material";
import InventoryIcon from '@mui/icons-material/Inventory';

function GoalCard({ icon, title, description }){
    return(
        <Card sx={{height: 200}}>
            <CardContent>
                <Stack justifyContent="center" alignItems="center" spacing={2}>
                    <Icon>{icon}</Icon>
                    <Typography variant="h6">{title}</Typography>
                    <Typography variant="body1" align="center">{description}</Typography>
                </Stack>
            </CardContent>
        </Card>
    )
}

export default GoalCard;