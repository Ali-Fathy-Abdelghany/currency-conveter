
# Currency Converter Application

## Overview

This Currency Converter Application is a JavaFX-based project that allows users to convert between various currencies using real-time exchange rates fetched from an external API. The application features a user-friendly interface with CSS styling for enhanced visual appeal.

### Key Features

- **Real-time Exchange Rates**: Exchange rates are refreshed daily using a reliable currency exchange API.
- **JavaFX**: The application is built using JavaFX, providing a modern, responsive, and interactive user interface.
- **CSS Styling**: The interface is styled using CSS to ensure a consistent and visually appealing look and feel.
- **Input Validation**: Ensures that only valid numerical inputs are accepted in the text fields.

## Prerequisites

Before running the application, ensure you have the following installed on your computer:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) (version 8 or higher)
- [JavaFX](https://openjfx.io/) (if not included in your JDK)
- An API key for the currency exchange service (sign up with your chosen API provider)

## Getting Started

### 1. Clone the Repository

Clone the project repository to your local machine using Git:

\`\`\`bash
git clone https://github.com/yourusername/currency-converter.git
cd currency-converter
\`\`\`

### 2. Set Up the API Key

The application requires an API key to fetch real-time exchange rates. Store your API key in an environment variable. Here are the steps:

#### Using an Environment Variable

**Windows:**

1. Open Command Prompt and set the environment variable:
   \`\`\`cmd
   setx API_KEY "YOUR_API_KEY"
   \`\`\`

2. Restart the Command Prompt.

**macOS and Linux:**

1. Open a terminal and set the environment variable for the current session:
   \`\`\`sh
   export API_KEY="YOUR_API_KEY"
   \`\`\`

2. To set it permanently, add the following line to your shell configuration file (e.g., \`~/.bashrc\`, \`~/.zshrc\`):
   \`\`\`sh
   export API_KEY="YOUR_API_KEY"
   \`\`\`

3. Source the file to apply the changes:
   \`\`\`sh
   source ~/.bashrc  # or the appropriate file
   \`\`\`

### 3. Directly Setting the API Key

You can also try it by putting the API key directly in your code. 

### 4. Modify the API URL Function (if needed)

If you are using another API provider than \`https://v6.exchangerate-api.com/v6/\`, make sure to modify the getURLString function:


### 5. Build and Run the Application

Compile and run the application using your IDE or command line.

#### Using Command Line

1. Navigate to the project directory:
   \`\`\`bash
   cd path/to/currency-converter
   \`\`\`

2. Compile the Java files:
   \`\`\`bash
   javac -d bin src/**/*.java
   \`\`\`

3. Run the application:
   \`\`\`bash
   java -cp bin com.yourpackage.Main
   \`\`\`

#### Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Set up the run configuration:
   - Go to \`Run > Edit Configurations\`.
   - Select your run configuration.
   - In the \`Configuration\` tab, add \`API_KEY\` to the \`Environment variables\` field.
3. Click \`Run\` to start the application.

## Usage

- **Input the amount**: Enter the amount you wish to convert in the input text field.
- **Select currencies**: Choose the source and target currencies from the dropdown menus.
- **Convert**: Click the "Convert" button to perform the conversion and display the result.


## Contributing

If you would like to contribute to the project, please fork the repository and submit a pull request.

## Contact

For any questions or feedback, please contact me on [LinkedIn](https://www.linkedin.com/in/ali-fathy-abdelghany) or [email](mailto:alifathy793@gmail.com).
