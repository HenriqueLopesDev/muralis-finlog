import type { Metadata } from 'next'
import { Montserrat, Poppins } from 'next/font/google'
import './globals.css'
import StoreProvider from '@/common/lib/redux/StoreProvider'
import { AppRouterCacheProvider } from '@mui/material-nextjs/v13-appRouter'

const montserrat = Montserrat({
  subsets: ['latin'],
  weight: ['400', '500', '600', '700'],
  display: 'swap',
  variable: '--font-montserrat',
})

const poppins = Poppins({
  subsets: ['latin'],
  weight: ['400', '500', '600', '700'],
  display: 'swap',
  variable: '--font-poppins',
})

export const metadata: Metadata = {
  title: 'Muralis FinLog | Início',
  description:
    'FinLog é um aplicativo da Muralis desenvolvido para ajudar no controle, organização e planejamento de gastos financeiros pessoais, oferecendo uma visão clara e eficiente das suas finanças.',
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang="pt-BR">
      <body
        className={`${montserrat.variable} ${poppins.variable} px-18 py-16  flex items-center justify-center max-[1200px]:px-14 max-[1200px]:py-12 max-[992px]:px-10 max-[992px]:py-8 max-[576px]:px-8`}
      >
        <StoreProvider>
          <AppRouterCacheProvider>
            <main
              style={{
                boxShadow:
                  'rgb(145, 158, 171, 0.04) 0px 0px 5px 0px, rgb(145, 158, 171, 0.22) 0px 12px 24px -4px',
              }}
              className="p-8 bg-gray-100 rounded-md w-full h-full min-h-[824px] flex max-[576px]:p-6"
            >
              {children}
            </main>
          </AppRouterCacheProvider>
        </StoreProvider>
      </body>
    </html>
  )
}
