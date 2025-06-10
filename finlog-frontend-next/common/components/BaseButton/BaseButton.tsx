import { BaseButtonProps } from './BaseButtonProps'

export function BaseButton({
  children,
  className = '',
  ...rest
}: BaseButtonProps) {
  return (
    <button
      className={`hover:cursor-pointer flex items-center justify-center gap-1 h-12 rounded-[10px] bg-[var(--color-01)] px-4 py-2 font-poppins font-semibold text-white transition-colors duration-200 hover:bg-[var(--color-02)]' +
        ${className}`}
      {...rest}
    >
      {children}
    </button>
  )
}
