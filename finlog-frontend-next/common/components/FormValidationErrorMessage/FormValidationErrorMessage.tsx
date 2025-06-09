import React from 'react'

export function FormValidationErrorMessage({
  children,
}: React.PropsWithChildren) {
  return <span className="font-poppins text-sm text-red-500">{children}</span>
}
