# Create an Android Ecommerce App with Medusa

This repository is the codebase of tutorial [Create an Android Ecommerce App with Medusa](https://www.notion.so/medusajs/Create-an-Android-Ecommerce-App-with-Medusa-96440c38d75841d197f003676d7a0989).

[Medusa Documentation](https://docs.medusajs.com/) | [Medusa Website](https://medusajs.com/) | [Medusa Repository](https://github.com/medusajs/medusa)

## Medusa Version

This tutorial uses Medusa v1.7.7. It is not guaranteed that it will work with future releases.

## Prerequisites

- [Node.js at least v14](https://docs.medusajs.com/tutorial/set-up-your-development-environment#nodejs)
- [Android Studio at least 2021.3.1](https://developer.android.com/studio/)
- [Yarn at least v1.22](https://classic.yarnpkg.com/lang/en/docs/install)

## How to Install

_You may change these steps per your article._

1. Clone this repository:

```bash
git clone https://github.com/arjunaskykok/medusa-android-app
```

2. Open it with Android Studio.

3. Install and run the Medusa server.

```bash
yarn global add @medusajs/medusa-cli
medusa new my-medusa-server --seed
cd my-medusa-server
medusa develop
```

4. Compile the Android project and run it in the Android emulator.
