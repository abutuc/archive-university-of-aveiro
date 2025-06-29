function lastTimeUpdated(postDate) {
    const date = new Date(postDate);
    const currentDate = new Date();
    currentDate.setTime(currentDate.getTime() + date.getTimezoneOffset() * 60 * 1000);

    const diff = Math.abs(currentDate - date);
    const minutes = Math.floor(diff / 1000 / 60);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);
  
    if (days > 0) {
        if (days === 1) {
            return "Yesterday";
        } else {
            return `${days} days ago`;
        }
    } else if (hours > 0) {
        if (hours === 1) {
            return "An hour ago";
        }
        return `${hours} hours ago`;
    } else if (minutes > 0) {
        if (minutes === 1) {
            return "A minute ago";
        }
        return `${minutes} minutes ago`;
    } else {
        return "Just now";
    }
}

function getMeasurementUnit(type) {
    switch (type) {
        case "airtemp":
            return "°C";
        case "airq":
            return "%";
        case "soilq":
            return "%";
        default:
            return "";
    }
}

export { lastTimeUpdated, getMeasurementUnit };